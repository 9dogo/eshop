package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import model.CommandLine;
import model.CommandLineId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


@Repository //annotation pour DAO
class DaoCommandLineJpaImpl implements DaoCommandLine {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void insert(CommandLine obj) {
		em.persist(obj);
	}

	@Override
	@Transactional
	public void update(CommandLine obj) {
		em.merge(obj);
	}

	@Override
	@Transactional
	public void delete(CommandLine obj) {
		em.remove(em.merge(obj));
	}

	@Override
	@Transactional
	public void deleteByKey(CommandLineId key) {
		em.remove(em.find(CommandLine.class, key));
	}

	@Override
	public CommandLine findByKey(CommandLineId key) {
		return em.find(CommandLine.class, key);
	}

	@Override
	public List<CommandLine> findAll() {
		TypedQuery<CommandLine> query = em.createQuery("from CommandLine e", CommandLine.class);
		return query.getResultList();
	}

}
