package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.model.Adress;

public interface AdressRepository extends JpaRepository<Adress, Long> {
}
