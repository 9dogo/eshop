package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.model.Command;

public interface CommandRepository extends JpaRepository<Command, Long> {
}
