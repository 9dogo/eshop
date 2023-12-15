package eshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eshop.model.Supplier;
import eshop.exceptions.SupplierException;
import eshop.exceptions.CheckId;
import eshop.repositories.SupplierRepository;

@Service
//traitement disponible sur les suppliers
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	public Supplier create(Supplier supplier) {
		CheckId.checkIdNull(supplier.getId());
		return supplierRepository.save(supplier);
	}

	public Supplier findById(Long id) {
		CheckId.checkIdNotNull(id);
		return supplierRepository.findById(id).orElseThrow(() -> {throw new SupplierException("unable to find the Supplier with id "+id);});
	}

	public List<Supplier> findAll() {
		return supplierRepository.findAll();
	}

	public Supplier update(Supplier supplier) {
		CheckId.checkIdNotNull(supplier.getId());
		return supplierRepository.save(supplier);
	}

	public void deleteById(Long id) {
		CheckId.checkIdNotNull(id);
		supplierRepository.delete(findById(id));
	}

	public void delete(Supplier supplier) {
		CheckId.checkIdNotNull(supplier.getId());
		deleteById(supplier.getId());
	}
	
	public Supplier findByName(String name) {
		return supplierRepository.findByName(name);
	}
	
	public Supplier findByIdWithProducts(Long id) {
		CheckId.checkIdNotNull(id);
		return supplierRepository.findByIdFetchProduct(id).orElseThrow(()->{throw new SupplierException("unable to find the Supplier with id "+id);
	});
	}
	
}