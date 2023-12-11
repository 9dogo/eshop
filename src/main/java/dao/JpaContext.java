package dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaContext {

    private static EntityManagerFactory emf = null;

    private static DaoSupplierJpaImpl daoSupplier = new DaoSupplierJpaImpl();
    private static DaoClientJpaImpl daoClient = new DaoClientJpaImpl();
    private static DaoCommandJpaImpl daoCommand = new DaoCommandJpaImpl();
    private static DaoCategoryJpaImpl daoCategory = new DaoCategoryJpaImpl();
    private static DaoProductJpaImpl daoProductJpaImpl = new DaoProductJpaImpl();

    public static DaoProductJpaImpl getDaoProductJpaImpl() {
        return daoProductJpaImpl;
    }

    public static DaoCategoryJpaImpl getDaoCategory() {
        return daoCategory;
    }

    public static DaoCommandJpaImpl getDaoCommand() {
        return daoCommand;
    }

    public static DaoClientJpaImpl getDaoClient() {
        return daoClient;
    }

    public static DaoSupplierJpaImpl getDaoSupplier() {
        return daoSupplier;
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf==null) {
            emf = Persistence.createEntityManagerFactory("jpa");
        }
        return emf;
    }

    public static void closeEntityManagerFactory() {
        emf.close();
    }
}