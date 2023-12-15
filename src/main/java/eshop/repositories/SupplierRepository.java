package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
