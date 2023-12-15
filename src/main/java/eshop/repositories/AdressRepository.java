package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.model.Adress;

public interface AdressRepository extends JpaRepository<Adress, Long> {
}
