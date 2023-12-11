package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Supplier;

// with a public visibility, instances of DaoSupplierJpaImpl could be created in App.java
// so we the set visibility to package, so we have to get the DaoSupplierJpaImpl from JpaContext
class DaoSupplierJpaImpl implements DaoSupplier {

    @Override
    public void insert(Supplier obj) {
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        // bloc connection to the database, every modification of the database has to be in a transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // make the object persistent : as long as we are in the transaction, every operation we make on this object will appear on the DB, ex :
            // em.persist(Supplier);
            // Supplier.setNom("blabla");
            // when we commit, the name of the Supplier will be blabla in the DB (as well as in the java code)
        em.persist(obj);  // generate a key automatically, if a key is already set to the obj -> crash
        // save changes of the DB
        tx.commit();
        em.close();
    }

    @Override
    public Supplier update(Supplier obj) {
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
    public void delete(Supplier obj) {
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
        em.remove(em.find(Supplier.class, key));  // get the updating object in the DB
        tx.commit();
        em.close();
    }

    @Override
    public Supplier findByKey(Long key) {
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        Supplier supplier = em.find(Supplier.class, key);
        em.close();
        return supplier;
    }

    @Override
    public List<Supplier> findAll() {
        List<Supplier> suppliers=null;
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        // Java Persistent Query Language -> query on an @Entity
        // we perform a select * to get the whole Entity, it is not interesting to get only a part of an @Entity
        // Query query = em.createQuery("from Supplier d"); // this work but the version bellow is a bit better
        // TypedQuery<> + argument Supplier.class : specify that the query as to return instances of supplier
        TypedQuery<Supplier> query = em.createQuery("from Supplier d",Supplier.class); // = select * from dept;
        suppliers = query.getResultList();
        em.close();
        return suppliers;
    }

    @Override
    public List<Supplier> findByCity(String city) {
        List<Supplier> suppliers=null;
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        // Java Persistent Query Language -> query on an @Entity
        // we perform a select * to get the whole Entity, it is not interesting to get only a part of an @Entity
        // Query query = em.createQuery("from Supplier d"); // this work but the version bellow is a bit better
        // TypedQuery<> + argument Supplier.class : specify that the query as to return instances of supplier
        TypedQuery<Supplier> query = em.createQuery("select s from Supplier s where s.adress.city=:city",Supplier.class); // = select * from dept;
        query.setParameter("city", city);
        suppliers = query.getResultList();
        em.close();
        return suppliers;
    }

    @Override
    public Supplier findByName(String name) {
        EntityManager em = JpaContext.getEntityManagerFactory().createEntityManager();
        TypedQuery<Supplier> query = em.createQuery("select sup from Supplier sup left join fetch sup.products p where sup.name=:name",Supplier.class); // = select * from dept;
        query.setParameter("name", name);
        Supplier category = null;
        try{
            category = query.getSingleResult();
        }
        catch(Exception e){e.printStackTrace();}
        return category;
    }
}