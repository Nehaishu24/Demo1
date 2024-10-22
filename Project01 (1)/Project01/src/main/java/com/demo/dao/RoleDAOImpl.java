package com.demo.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import com.demo.dto.RoleDTO;

@Repository
public class RoleDAOImpl implements RoleDAOInt {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long add(RoleDTO dto) {
        entityManager.persist(dto);
        return dto.getId();
    }

    @Override
    public void update(RoleDTO dto) {
        entityManager.merge(dto);
    }

    @Override
    public void delete(RoleDTO dto) {
        entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
    }

    @Override
    public RoleDTO findByPk(long pk) {
        return entityManager.find(RoleDTO.class, pk);
    }

    @Override
    public List<RoleDTO> search(RoleDTO dto, int pageNo, int pageSize) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RoleDTO> cq = builder.createQuery(RoleDTO.class);
        Root<RoleDTO> root = cq.from(RoleDTO.class);
        List<Predicate> predicates = new ArrayList<>();

        if (dto != null && dto.getName() != null && !dto.getName().isEmpty()) {
            predicates.add(builder.equal(root.get("name"), dto.getName()));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        TypedQuery<RoleDTO> query = entityManager.createQuery(cq);

        if (pageSize > 0) {
            query.setFirstResult(pageNo * pageSize);
            query.setMaxResults(pageSize);
        }

        return query.getResultList();
    }
}
