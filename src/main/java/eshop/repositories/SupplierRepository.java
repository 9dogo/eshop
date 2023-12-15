package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import project.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
