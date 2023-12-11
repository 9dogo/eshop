package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.CommandLine;
import model.CommandLineId;

// with a public visibility, instances of DaoCommandLineJpaImpl could be created in App.java
// so we the set visibility to package, so we have to get the DaoCommandLineJpaImpl from JpaContext
class DaoCommandLineJpaImpl implements DaoCommandLine {

    @Override
    public void insert(CommandLine obj) {
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        // bloc connection to the database, every modification of the database has to be in a transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // make the object persistent : as long as we are in the transaction, every operation we make on this object will appear on the DB, ex :
            // em.persist(CommandLine);
            // CommandLine.setNom("blabla");
            // when we commit, the name of the CommandLine will be blabla in the DB (as well as in the java code)
        em.persist(obj);  // generate a key automatically, if a key is already set to the obj -> crash
        // save changes of the DB
        tx.commit();
        em.close();
    }

    @Override
    public void update(CommandLine obj) {
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // merge links an object to the database, if he doesn't find the object, he inserts a new one 
        // (unlike persist, ha can insert an  object which already have a key)
        obj = em.merge(obj);  
        tx.commit();
        em.close();
    }

    @Override
    public void delete(CommandLine obj) {
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(em.merge(obj));  // we call a merge to guarantee that the obj in argument is coming from the base
        tx.commit();
        em.close();
    }

    @Override
    public void deleteByKey(CommandLineId key) {
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(em.find(CommandLine.class, key));  // get the updating object in the DB
        tx.commit();
        em.close();
    }

    @Override
    public CommandLine findByKey(CommandLineId key) {
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        CommandLine command = em.find(CommandLine.class, key);
        em.close();
        return command;
    }

    @Override
    public List<CommandLine> findAll() {
        List<CommandLine> commandLines=null;
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        // Java Persistent Query Language -> query on an @Entity
        // we perform a select * to get the whole Entity, it is not interesting to get only a part of an @Entity
        // Query query = em.createQuery("from CommandLine d"); // this work but the version bellow is a bit better
        // TypedQuery<> + argument CommandLine.class : specify that the query as to return instances of command
        TypedQuery<CommandLine> query = em.createQuery("from CommandLine d",CommandLine.class); // = select * from dept;
        commandLines = query.getResultList();
        em.close();
        return commandLines;
    }
}