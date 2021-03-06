package dev.topcollegues.api.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.topcollegues.api.entite.Collegue;
import dev.topcollegues.api.exception.CollegueAlreadyExistsException;
import dev.topcollegues.api.exception.CollegueBadPseudoException;
import dev.topcollegues.api.exception.CollegueException;
import dev.topcollegues.api.repository.CollegueRepository;

@RestController
@CrossOrigin
@RequestMapping(path = "/collegues")
public class CollegueController {
	
	@Autowired
	CollegueRepository collegueRepository;
	
	
	@GetMapping
	public List<Collegue> listerCollegue() {
		return collegueRepository.findAll();
	}
	
	
	@PostMapping
	public List<Collegue> ajouterCollegue(@RequestBody Collegue collegue) throws CollegueException  {

		if(collegue.getPseudo() == null || collegue.getPseudo().trim().isEmpty()) {
			throw new CollegueBadPseudoException("The pseudo is incorrect");
		}
		
		collegue.setPseudo(collegue.getPseudo().trim().toLowerCase());
		
		Optional<Collegue> optional = collegueRepository.findByPseudo(collegue.getPseudo());
		
		if(optional.isPresent()) {
			throw new CollegueAlreadyExistsException("The pseudo "+ collegue.getPseudo() +" is already taken");
		}
		
		collegueRepository.save(collegue);
	
		return collegueRepository.findAll();
	}
	
}
