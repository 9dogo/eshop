package dao;

import java.util.List;

import model.Supplier;

public interface DaoSupplier extends DaoGeneric<Supplier,Long>{
    List<Supplier> findByCity(final String city);
    Supplier findByName(String name);
}