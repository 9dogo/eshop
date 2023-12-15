package eshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eshop.model.Command;
import eshop.exceptions.CommandException;
import eshop.exceptions.CheckId;
import eshop.repositories.CommandRepository;

@Service
//traitement disponible sur les commands
public class CommandService {

	@Autowired
	private CommandRepository commandRepository;
	
	public Command create(Command command) {
		CheckId.checkIdNull(command.getId());
		return commandRepository.save(command);
	}

	public Command findById(Long id) {
		CheckId.checkIdNotNull(id);
		return commandRepository.findById(id).orElseThrow(() -> {throw new CommandException("unable to find the Command with id "+id);});
	}

	public List<Command> findAll() {
		return commandRepository.findAll();
	}

	public Command update(Command command) {
		CheckId.checkIdNotNull(command.getId());
		return commandRepository.save(command);
	}

	public void deleteById(Long id) {
		CheckId.checkIdNotNull(id);
		commandRepository.delete(findById(id));
	}

	public void delete(Command command) {
		CheckId.checkIdNotNull(command.getId());
		deleteById(command.getId());
	}

	public Command findByIdWithCommandLine(Long id) {
		CheckId.checkIdNotNull(id);
		return commandRepository.findByNumeroFetchLignesCommande(id);
	}
}