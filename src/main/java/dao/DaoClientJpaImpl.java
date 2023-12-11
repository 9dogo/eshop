package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Client;

// with a public visibility, instances of DaoClientJpaImpl could be created in App.java
// so we the set visibility to package, so we have to get the DaoClientJpaImpl from JpaContext
class DaoClientJpaImpl implements DaoClient {

    @Override
    public void insert(Client obj) {
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        // bloc connection to the database, every modification of the database has to be in a transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // make the object persistent : as long as we are in the transaction, every operation we make on this object will appear on the DB, ex :
            // em.persist(Client);
            // Client.setNom("blabla");
            // when we commit, the name of the Client will be blabla in the DB (as well as in the java code)
        em.persist(obj);  // generate a key automatically, if a key is already set to the obj -> crash
        // save changes of the DB
        tx.commit();
        em.close();
    }

    @Override
    public void update(Client obj) {
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
    public void delete(Client obj) {
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(em.merge(obj));  // we call a merge to guarantee that the obj in argument is coming from the base
        tx.commit();
        em.close();
    }

    @Override
    public void deleteByKey(Long key) {
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(em.find(Client.class, key));  // get the updating object in the DB
        tx.commit();
        em.close();
    }

    @Override
    public Client findByKey(Long key) {
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        Client client = em.find(Client.class, key);
        em.close();
        return client;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients=null;
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        // Java Persistent Query Language -> query on an @Entity
        // we perform a select * to get the whole Entity, it is not interesting to get only a part of an @Entity
        // Query query = em.createQuery("from Client d"); // this work but the version bellow is a bit better
        // TypedQuery<> + argument Client.class : specify that the query as to return instances of client
        TypedQuery<Client> query = em.createQuery("from Client d",Client.class); // = select * from dept;
        clients = query.getResultList();
        em.close();
        return clients;
    }
}