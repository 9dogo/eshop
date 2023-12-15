package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.model.Command;

public interface CommandRepository extends JpaRepository<Command, Long> {
}
