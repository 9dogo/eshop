package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.model.CommandLine;
import eshop.model.CommandLineId;

public interface CommandLineRepository extends JpaRepository<CommandLine, CommandLineId> {
}
