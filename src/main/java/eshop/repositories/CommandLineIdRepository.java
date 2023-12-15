package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.model.CommandLineId;

public interface CommandLineIdRepository extends JpaRepository<CommandLineId, Long> {
}
