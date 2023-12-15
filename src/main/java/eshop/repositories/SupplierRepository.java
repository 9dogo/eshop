package eshop.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eshop.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
	@Query("select s from Supplier s left join fetch s.products p where s.id=:id")
	Optional<Supplier> findByIdFetchProduct(@Param("id") Long id);
	Supplier findByName(String name);
	
}
