package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eshop.model.Client;
import eshop.model.Command;
import jakarta.transaction.Transactional;

public interface CommandRepository extends JpaRepository<Command, Long> {
	
	@Modifying
	@Transactional
	void deleteByClient(Client client);
	@Query("select c from Command c left join fetch c.lines where c.id=:id")
	Command findByNumeroFetchLignesCommande(@Param("id") Long id);
	
	
}
