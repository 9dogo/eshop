package eshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eshop.model.Client;
import eshop.exceptions.ClientException;
import eshop.repositories.ClientRepository;

@Service
//traitement disponible sur les clients
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public Client create(Client client) {
		CheckId.checkIdNull(client.getId());
		return clientRepository.save(client);
	}

	public Client findById(Long id) {
		CheckId.checkIdNull(id);
		return clientRepository.findById(id).orElseThrow(() -> {throw new ClientException("unable to find the Client with id "+id);});
	}

	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	public Client update(Client client) {
		CheckId.checkIdNull(client.getId());
		return clientRepository.save(client);
	}

	public void deleteById(Long id) {
		CheckId.checkIdNull(id);
		clientRepository.delete(findById(id));
	}

	public void delete(Client client) {
		CheckId.checkIdNull(client.getId());
		deleteById(client.getId());
	}
}