package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import model.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


@Repository //annotation pour DAO
class DaoCategoryJpaImpl implements DaoCategory {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void insert(Category obj) {
		em.persist(obj);
	}

	@Override
	@Transactional
	public void update(Category obj) {
		em.merge(obj);
	}

	@Override
	@Transactional
	public void delete(Category obj) {
		em.remove(em.merge(obj));
	}

	@Override
	@Transactional
	public void deleteByKey(Long key) {
		em.remove(em.find(Category.class, key));
	}

	@Override
	public Category findByKey(Long key) {
		return em.find(Category.class, key);
	}

	@Override
	public List<Category> findAll() {
		TypedQuery<Category> query = em.createQuery("from Category e", Category.class);
		return query.getResultList();
	}

}
