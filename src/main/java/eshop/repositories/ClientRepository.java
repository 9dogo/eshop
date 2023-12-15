package eshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eshop.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	List<Client> findByName(String name);
	List<Client> findByFirstName(String firstName);
	List<Client> findByNameAndFirstName(String name, String FirstName);
	@Query("select c from Client c left join fetch c.Command where c.id = :id")
	Client findByIdFetchCommand(@Param("id") Long id);
	

}
