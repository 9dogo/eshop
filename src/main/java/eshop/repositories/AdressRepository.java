package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.entities.Adress;

public interface AdressRepository extends JpaRepository<Adress, Long> {
}
