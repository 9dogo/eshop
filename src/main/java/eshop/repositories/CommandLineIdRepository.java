package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.model.CommandLineId;

public interface CommandLineIdRepository extends JpaRepository<CommandLineId, Long> {
}
