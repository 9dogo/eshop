package dao;

import java.util.List;

import model.Client;

public interface DaoClient extends DaoGeneric<Client,Long>{
    List<Client> findByName(final String name);
    List<Client> findByNameContaining(String name);
    Client findByIdFetchCommands(Long id);
    List<Client> findByCommandYear(String year);
}