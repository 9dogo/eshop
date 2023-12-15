package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
