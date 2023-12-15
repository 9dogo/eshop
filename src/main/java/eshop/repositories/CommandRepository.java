package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import eshop.model.Client;
import eshop.model.Command;
import jakarta.transaction.Transactional;

public interface CommandRepository extends JpaRepository<Command, Long> {
	
	@Modifying
	@Transactional
	void deleteByClient(Client client);
}
