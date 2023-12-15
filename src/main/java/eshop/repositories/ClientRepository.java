package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
