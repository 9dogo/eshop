package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.model.CommandLine;

public interface CommandLineRepository extends JpaRepository<CommandLine, Long> {
}
