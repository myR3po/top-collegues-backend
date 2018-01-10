package dev.topcollegues.api.service;

import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.topcollegues.api.entite.Collegue;
import dev.topcollegues.api.repository.CollegueRepository;


@Service
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	CollegueRepository collegueRepository;
	
	public InitialiserDonneesServiceDev(){
		
	}

	@Override
	@Transactional
	public void initialiser() {
		final String url = "https://avatars0.githubusercontent.com/u/18171845?s=400&v=4";
		
		
		Stream.of("jean", "pierre", "christophe", "caroline", "michelle").forEach(name -> {
			Collegue c = new Collegue();
		    c.setPseudo(name);
		    c.setUrl(url);
		    c.setScore(50);
		    em.persist(c);
			
		});
			    
	}

}
