package eshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.model.Adress;
import project.exceptions.AdressException;
import project.exceptions.CheckId;
import project.repositories.AdressRepository;

@Service
//traitement disponible sur les adresss
public class AdressService {

	@Autowired
	private AdressRepository adressRepository;

	public Adress create(Adress adress) {
		CheckId.checkIdNull(adress.getId());
		return adressRepository.save(adress);
	}

	public Adress findById(Long id) {
		CheckId.checkIdNotNull(id);
		return adressRepository.findById(id).orElseThrow(() -> {throw new AdressException("unable to find the Adress with id "+id);});
	}

	public List<Adress> findAll() {
		return adressRepository.findAll();
	}

	public Adress update(Adress adress) {
		CheckId.checkIdNotNull(adress.getId());
		return adressRepository.save(adress);
	}

	public void deleteById(Long id) {
		CheckId.checkIdNotNull(id);
		adressRepository.delete(findById(id));
	}

	public void delete(Adress adress) {
		CheckId.checkIdNotNull(adress.getId());
		deleteById(adress.getId());
	}
}