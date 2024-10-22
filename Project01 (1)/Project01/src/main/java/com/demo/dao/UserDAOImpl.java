package com.demo.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import com.demo.dto.UserDTO;

@Repository
public class UserDAOImpl implements UserDAOInt {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public long add(UserDTO dto) {
		entityManager.persist(dto);
		return dto.getId();
	}

	@Override
	public void update(UserDTO dto) {
		entityManager.merge(dto);
	}

	@Override
	public void delete(UserDTO dto) {
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
	}

	@Override
	public UserDTO findByPk(long pk) {
		return entityManager.find(UserDTO.class, pk);
	}

	
	@Override
	public UserDTO findUserByEmail(String email) {
		try {
			TypedQuery<UserDTO> query = entityManager.createQuery("SELECT u FROM UserDTO u WHERE u.email = :email",
					UserDTO.class);
			query.setParameter("email", email);
			return query.getSingleResult();
		} catch (NoResultException e) {
			
			return null;
		}
	}

	@Override
	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserDTO> cq = builder.createQuery(UserDTO.class);
		Root<UserDTO> root = cq.from(UserDTO.class);
		List<Predicate> predicates = new ArrayList<>();

		if (dto != null) {
			if (dto.getUsername() != null && !dto.getUsername().isEmpty()) {
				predicates.add(builder.like(root.get("username"), dto.getUsername() + "%"));
			}
			if (dto.getEmail() != null && !dto.getEmail().isEmpty()) {
				predicates.add(builder.equal(root.get("email"), dto.getEmail()));
			}
		}

		cq.where(predicates.toArray(new Predicate[0]));
		TypedQuery<UserDTO> query = entityManager.createQuery(cq);

		if (pageSize > 0) {
			query.setFirstResult(pageNo * pageSize);
			query.setMaxResults(pageSize);
		}

		return query.getResultList();
	}

}
