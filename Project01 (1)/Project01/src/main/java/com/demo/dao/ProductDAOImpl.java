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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.demo.dto.ProductDTO;
import com.demo.dto.UserDTO;

@Repository
public class ProductDAOImpl implements ProductDAOInt {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public long add(ProductDTO dto) {
		entityManager.persist(dto);
		return dto.getId();
	}

	@Override
	public void update(ProductDTO dto) {
		entityManager.merge(dto);
	}

	@Override
	public void delete(ProductDTO dto) {
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
	}

	@Override
	public ProductDTO findByPk(long pk) {
		return entityManager.find(ProductDTO.class, pk);
	}

	@Override
	public List<ProductDTO> search(ProductDTO dto, int pageNo, int pageSize) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ProductDTO> cq = builder.createQuery(ProductDTO.class);
		Root<ProductDTO> root = cq.from(ProductDTO.class);
		List<Predicate> predicates = new ArrayList<>();

		if (dto != null) {

			if (dto.getProductName() != null && !dto.getProductName().isEmpty()) {
				predicates.add(builder.like(root.get("name"), dto.getProductName() + "%"));

			}

		}

		cq.where(predicates.toArray(new Predicate[0]));
		TypedQuery<ProductDTO> query = entityManager.createQuery(cq);

		if (pageSize > 0) {
			query.setFirstResult(pageNo * pageSize);
			query.setMaxResults(pageSize);
		}

		return query.getResultList();
	}
}
