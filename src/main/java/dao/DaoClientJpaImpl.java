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
    public Client update(Client obj) {
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
    public void delete(Client obj) {
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        JpaContext.getDaoCommand().updateClientToNull(obj);
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
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        // Java Persistent Query Language -> query on an @Entity
        // we perform a select * to get the whole Entity, it is not interesting to get only a part of an @Entity
        // Query query = em.createQuery("from Client d"); // this work but the version bellow is a bit better
        // TypedQuery<> + argument Client.class : specify that the query as to return instances of client
        TypedQuery<Client> query = em.createQuery("from Client d",Client.class); // = select * from dept;
        List<Client> clients = query.getResultList();
        em.close();
        return clients;
    }

    @Override
    public List<Client> findByName(String name) {
        List<Client> clients=null;
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        // c.name : calls Client.getName() for every instances of Client return in the query
        // =:name : set a variable in the query, as with a JDBC preparedStatement
        TypedQuery<Client> query = em.createQuery("select c from Client c where c.name=:name",Client.class); // = select * from dept;
        // set the parameter "name" in the query to the value name
        // we could also have written query.setParameter(0, name);
        query.setParameter("name", name);
        clients = query.getResultList();
        em.close();
        return clients;
    }

    @Override
    public List<Client> findByNameContaining(String name) {
        List<Client> clients=null;
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        // c.name : calls Client.getName() for every instances of Client return in the query
        // =:name : set a variable in the query, as with a JDBC preparedStatement
        TypedQuery<Client> query = em.createQuery("select c from Client c where c.name=:name",Client.class); // = select * from dept;
        // we are looking for instances of Client where getName() contains the string 'name'
        query.setParameter("name", "%"+name+"%");
        clients = query.getResultList();
        em.close();
        return clients;
    }

    @Override
    // get the Client of id "id" as long as his commands
    public Client findByIdFetchCommands(Long id) {
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        // left join fetch : load the list of command as long as the client, with just a fetch, this list is not loaded
            // left : if the client doesn't have any command, he is still return by the join
        TypedQuery<Client> query = em.createQuery("select cli from Client cli left join fetch Command com where cli.id=:id",Client.class); // = select * from dept;
        // we are looking for instances of Client where getName() contains the string 'name'
        query.setParameter("id", id);
        // getSingleResult : return only one result : /!\ execpetion if several or 0 results
        Client client = null;
        try {
            client = query.getSingleResult();
        } catch (Exception e) {e.printStackTrace();}
        em.close();
        return client;
    }

    @Override
    // get the Client of id "id" as long as his commands
    public List<Client> findByCommandYear(String year) {
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        // c.commandes : get all the commands of a specific client
        // we have to specify aliases to make where clause
        TypedQuery<Client> query = em.createQuery("select cli from Client cli left join fetch c.command com where year(com.date)=:year",Client.class); // = select * from dept;
        query.setParameter("year", year);
        List<Client> clients = query.getResultList();
        em.close();
        return clients;
    }
}