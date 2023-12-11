package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.Client;
import model.Command;

// with a public visibility, instances of DaoCommandJpaImpl could be created in App.java
// so we the set visibility to package, so we have to get the DaoCommandJpaImpl from JpaContext
class DaoCommandJpaImpl implements DaoCommand {

    @Override
    public void insert(Command obj) {
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        // bloc connection to the database, every modification of the database has to be in a transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // make the object persistent : as long as we are in the transaction, every operation we make on this object will appear on the DB, ex :
            // em.persist(Command);
            // Command.setNom("blabla");
            // when we commit, the name of the Command will be blabla in the DB (as well as in the java code)
        em.persist(obj);  // generate a key automatically, if a key is already set to the obj -> crash
        // save changes of the DB
        tx.commit();
        em.close();
    }

    @Override
    public Command update(Command obj) {
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // merge links an object to the database, if he doesn't find the object, he inserts a new one 
        // (unlike persist, ha can insert an  object which already have a key)
        obj = em.merge(obj);  
        tx.commit();
        em.close();
        return obj;
    }

    @Override
    public void delete(Command obj) {
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(em.merge(obj));  // we call a merge to guarantee that the obj in argument is coming from the base
        tx.commit();
        em.close();
    }

    @Override
    public void deleteByKey(Long key) {
        delete(findByKey(key));
    }

    @Override
    public Command findByKey(Long key) {
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        Command command = em.find(Command.class, key);
        em.close();
        return command;
    }

    @Override
    public List<Command> findAll() {
        List<Command> commands=null;
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        // Java Persistent Query Language -> query on an @Entity
        // we perform a select * to get the whole Entity, it is not interesting to get only a part of an @Entity
        // Query query = em.createQuery("from Command d"); // this work but the version bellow is a bit better
        // TypedQuery<> + argument Command.class : specify that the query as to return instances of command
        TypedQuery<Command> query = em.createQuery("from Command d",Command.class); // = select * from dept;
        commands = query.getResultList();
        em.close();
        return commands;
    }

    @Override
    public void deleteByClient(final Client client) {
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery("delete from Command com where com.client=:client");
        // em.executeUpdate(query);
        query.setParameter(0, client);
        query.executeUpdate();
        tx. commit();
        em.close();
    }

    @Override
    public void updateClientToNull(Client client) {
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery("update from Command com set com.client=null where com.client=:client");
        // em.executeUpdate(query);
        query.setParameter(0, client);
        query.executeUpdate();
        tx. commit();
        em.close();
    }
}