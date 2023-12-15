package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.entities.CommandLineId;

public interface CommandLineIdRepository extends JpaRepository<CommandLineId, Long> {
}
