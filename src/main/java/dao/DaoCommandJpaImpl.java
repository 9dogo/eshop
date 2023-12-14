package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import model.Command;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


@Repository //annotation pour DAO
class DaoCommandJpaImpl implements DaoCommand {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void insert(Command obj) {
		em.persist(obj);
	}

	@Override
	@Transactional
	public void update(Command obj) {
		em.merge(obj);
	}

	@Override
	@Transactional
	public void delete(Command obj) {
		em.remove(em.merge(obj));
	}

	@Override
	@Transactional
	public void deleteByKey(Long key) {
		em.remove(em.find(Command.class, key));
	}

	@Override
	public Command findByKey(Long key) {
		return em.find(Command.class, key);
	}

	@Override
	public List<Command> findAll() {
		TypedQuery<Command> query = em.createQuery("from Command e", Command.class);
		return query.getResultList();
	}

}
