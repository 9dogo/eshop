package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.entities.CommandLine;

public interface CommandLineRepository extends JpaRepository<CommandLine, Long> {
}
