package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
