package dev.topcollegues.api.controller;

import dev.topcollegues.api.entite.Collegue;
import dev.topcollegues.api.entite.Opinion;
import dev.topcollegues.api.exception.CollegueBadPseudoException;
import dev.topcollegues.api.exception.CollegueException;
import dev.topcollegues.api.exception.CollegueNotFoundException;
import dev.topcollegues.api.repository.CollegueRepository;
import dev.topcollegues.api.repository.OpinionRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path = "/comments")
public class OpinionController {

  @Autowired
  private CollegueRepository collegueRepository;

  @Autowired
  private OpinionRepository opinionRepository;

  @GetMapping("/{pseudo}")
  public List<Opinion> listerOpinions(@PathVariable String pseudo) throws CollegueException {
	  
	  if (pseudo == null ||pseudo.trim().isEmpty()) {
	      throw new CollegueBadPseudoException("The param can not be null");
	    }
	  
	  Collegue c = collegueRepository.findByPseudo(pseudo.trim()).orElseThrow(() -> new CollegueNotFoundException("Could not found the collegue"));
	  
   	return opinionRepository.findByCollegue(c);
  }

  @PostMapping
  public void commenter(@RequestBody Map<String, String> params) throws CollegueException {

	String pseudo = params.get("pseudo");
	String commentaire = params.get("commentaire");
	
    
    if (commentaire == null || commentaire.trim().isEmpty()){
      throw new CollegueException("No commentary has been provided");
    }

    if (pseudo == null ||pseudo.trim().isEmpty()) {
	      throw new CollegueBadPseudoException("The param can not be null");
	}
    
    Collegue collegue = collegueRepository.findByPseudo(pseudo.trim()).orElseThrow(() -> new CollegueNotFoundException("Could not found the collegue"));
        
    Opinion opinion = new Opinion();
    opinion.setCollegue(collegue);
    opinion.setCommentaire(commentaire);    
 	opinionRepository.save(opinion);

  }
  
}
