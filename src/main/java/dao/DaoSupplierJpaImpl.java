package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import model.Supplier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


@Repository //annotation pour DAO
class DaoSupplierJpaImpl implements DaoSupplier {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void insert(Supplier obj) {
		em.persist(obj);
	}

	@Override
	@Transactional
	public void update(Supplier obj) {
		em.merge(obj);
	}

	@Override
	@Transactional
	public void delete(Supplier obj) {
		em.remove(em.merge(obj));
	}

	@Override
	@Transactional
	public void deleteByKey(Long key) {
		em.remove(em.find(Supplier.class, key));
	}

	@Override
	public Supplier findByKey(Long key) {
		return em.find(Supplier.class, key);
	}

	@Override
	public List<Supplier> findAll() {
		TypedQuery<Supplier> query = em.createQuery("from Supplier e", Supplier.class);
		return query.getResultList();
	}

	@Override
	public List<Supplier> findByCity(String city) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findByCity'");
	}

	@Override
	public Supplier findByName(String name) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findByName'");
	}

	@Override
	public List<Supplier> findByDepartement(String departement) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findByDepartement'");
	}

}
