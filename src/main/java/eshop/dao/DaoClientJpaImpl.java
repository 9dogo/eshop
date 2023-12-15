package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import model.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


@Repository //annotation pour DAO
class DaoClientJpaImpl implements DaoClient {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void insert(Client obj) {
		em.persist(obj);
	}

	@Override
	@Transactional
	public void update(Client obj) {
		em.merge(obj);
	}

	@Override
	@Transactional
	public void delete(Client obj) {
		em.remove(em.merge(obj));
	}

	@Override
	@Transactional
	public void deleteByKey(Long key) {
		em.remove(em.find(Client.class, key));
	}

	@Override
	public Client findByKey(Long key) {
		return em.find(Client.class, key);
	}

	@Override
	public List<Client> findAll() {
		TypedQuery<Client> query = em.createQuery("from Client e", Client.class);
		return query.getResultList();
	}

	@Override
	public List<Client> findByName(String name) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findByName'");
	}

	@Override
	public List<Client> findByNameContaining(String name) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findByNameContaining'");
	}

	@Override
	public Client findByIdFetchCommands(Long id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findByIdFetchCommands'");
	}

	@Override
	public List<Client> findByCommandYear(String year) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findByCommandYear'");
	}

}
