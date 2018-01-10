package dev.topcollegues.api.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.topcollegues.api.entite.Collegue;
import dev.topcollegues.api.entite.Opinion;
import dev.topcollegues.api.exception.CollegueAlreadyExistsException;
import dev.topcollegues.api.exception.CollegueBadPseudoException;
import dev.topcollegues.api.exception.CollegueException;
import dev.topcollegues.api.exception.CollegueNotFoundException;
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
			throw new CollegueBadPseudoException("");
		}
		
		collegue.setPseudo(collegue.getPseudo().trim().toLowerCase());
		
		Optional<Collegue> optional = collegueRepository.findByPseudo(collegue.getPseudo());
		
		if(optional.isPresent()) {
			throw new CollegueAlreadyExistsException("");
		}
		
		collegueRepository.save(collegue);
	
		return collegueRepository.findAll();
	}
	
	@PatchMapping(path = "/{pseudo}")
	public Collegue mettreScoreCollegueAjour(@RequestBody Opinion opinion, @PathVariable("pseudo") String pseudo) throws CollegueException   {
		
		if(pseudo == null || pseudo.trim().isEmpty()) {
			throw new CollegueBadPseudoException("");
		}
		
		Optional<Collegue> optional =  collegueRepository.findByPseudo(pseudo.trim());
		
		Collegue collegue = optional.orElseThrow(() ->  new CollegueNotFoundException());

		if(opinion.getAction().equalsIgnoreCase("aimer")) {
			collegue.setScore(collegue.getScore() + 10);
		}else {
			collegue.setScore(collegue.getScore() - 5);
		}
		
		collegueRepository.save(collegue);


		return collegue;
	}
	
}
