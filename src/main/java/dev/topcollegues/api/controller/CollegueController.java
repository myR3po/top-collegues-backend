package dev.topcollegues.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.topcollegues.api.entite.Collegue;
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
	

	
}
