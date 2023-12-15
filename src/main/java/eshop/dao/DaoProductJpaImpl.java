package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


@Repository //annotation pour DAO
class DaoProductJpaImpl implements DaoProduct {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void insert(Product obj) {
		em.persist(obj);
	}

	@Override
	@Transactional
	public void update(Product obj) {
		em.merge(obj);
	}

	@Override
	@Transactional
	public void delete(Product obj) {
		em.remove(em.merge(obj));
	}

	@Override
	@Transactional
	public void deleteByKey(Long key) {
		em.remove(em.find(Product.class, key));
	}

	@Override
	public Product findByKey(Long key) {
		return em.find(Product.class, key);
	}

	@Override
	public List<Product> findAll() {
		TypedQuery<Product> query = em.createQuery("from Product e", Product.class);
		return query.getResultList();
	}

}
