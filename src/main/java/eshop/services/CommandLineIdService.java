package eshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.model.CommandLineId;
import project.exceptions.CommandLineIdException;
import project.exceptions.CheckId;
import project.repositories.CommandLineIdRepository;

@Service
//traitement disponible sur les commandlineids
public class CommandLineIdService {

	@Autowired
	private CommandLineIdRepository commandlineidRepository;

	public CommandLineId create(CommandLineId commandlineid) {
		CheckId.checkIdNull(commandlineid.getId());
		return commandlineidRepository.save(commandlineid);
	}

	public CommandLineId findById(Long id) {
		CheckId.checkIdNotNull(id);
		return commandlineidRepository.findById(id).orElseThrow(() -> {throw new CommandLineIdException("unable to find the CommandLineId with id "+id);});
	}

	public List<CommandLineId> findAll() {
		return commandlineidRepository.findAll();
	}

	public CommandLineId update(CommandLineId commandlineid) {
		CheckId.checkIdNotNull(commandlineid.getId());
		return commandlineidRepository.save(commandlineid);
	}

	public void deleteById(Long id) {
		CheckId.checkIdNotNull(id);
		commandlineidRepository.delete(findById(id));
	}

	public void delete(CommandLineId commandlineid) {
		CheckId.checkIdNotNull(commandlineid.getId());
		deleteById(commandlineid.getId());
	}
}