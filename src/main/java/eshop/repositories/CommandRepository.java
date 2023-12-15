package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.entities.Command;

public interface CommandRepository extends JpaRepository<Command, Long> {
}
