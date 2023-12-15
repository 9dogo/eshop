package eshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eshop.model.Client;
import eshop.exceptions.CheckId;
import eshop.exceptions.ClientException;
import eshop.repositories.ClientRepository;
import eshop.repositories.CommandRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CommandRepository commandRepository;
	
	
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
		commandRepository.deleteByClient(findById(id));
		clientRepository.delete(findById(id));
	}

	public void delete(Client client) {
		CheckId.checkIdNull(client.getId());
		deleteById(client.getId());
	}
	
	public List<Client> findByName(String name) {
		List<Client> clients = clientRepository.findByName(name);
		if (clients.isEmpty()) {
			throw new ClientException("client name not in table");
		}
		return clients;
	}
	
	public List<Client> findByFirstName(String firstName) {
		List<Client> clients = clientRepository.findByFirstName(firstName);
		if (clients.isEmpty()) {
			throw new ClientException("client first name not in table");
		}
		return clients;
	}
	
	public List<Client> findByNameAndFirstName(String name, String firstName) {
		List<Client> clients = clientRepository.findByNameAndFirstName(name, firstName);
		if (clients.isEmpty()) {
			throw new ClientException("client not in table");
		}
		return clients;
	}
	
	public Client findByIdWithCommand(Long id) {
		CheckId.checkIdNull(id);
		return clientRepository.findByIdFetchCommand(id);
	}
}