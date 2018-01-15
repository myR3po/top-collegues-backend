package dev.topcollegues.api.controller;

import dev.topcollegues.api.entite.Collegue;
import dev.topcollegues.api.entite.Vote;
import dev.topcollegues.api.exception.CollegueException;
import dev.topcollegues.api.exception.CollegueNotFoundException;
import dev.topcollegues.api.repository.CollegueRepository;
import dev.topcollegues.api.repository.VoteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path = "/votes")
public class VoteController {

  @Autowired
  private CollegueRepository collegueRepository;

  @Autowired
  private VoteRepository voteRepository;

  @GetMapping
  public List<Vote> listerCollegue(@RequestParam(value = "since", required = false) Integer voteId) {
    
	List<Vote> votes = null;
	
    if(voteId != null) {
    	votes = voteRepository.findByIdGreaterThanOrderByIdDesc(voteId);
    }
    
    if(votes == null || votes.isEmpty()) {
    	votes =  voteRepository.findFirst3ByOrderByIdDesc();
    }
    return votes;
  }

  @PatchMapping
  public Collegue voter(@RequestBody Vote vote) throws CollegueException {

    if (vote == null) {
      throw new CollegueException("No vote has been provided");
    }

    Collegue collegue = checkCollegueFromRequest(vote.getCollegue());
    
    if (vote.getAvis() == null){
      throw new CollegueException("No vote has been provided");
    }else {
      vote.setCollegue(collegue);
      if (vote.getAvis().equalsIgnoreCase("aimer")) {
        vote.getCollegue().setScore(vote.getCollegue().getScore() + 10);
      } else {
        vote.getCollegue().setScore(vote.getCollegue().getScore() - 5);
      }
    }
    
    voteRepository.save(vote);

    return collegue;
  }

  private Collegue checkCollegueFromRequest(Collegue collegueFromRequest) throws CollegueNotFoundException {

    if (collegueFromRequest == null) {
      throw new CollegueNotFoundException("The collegue could not be null");
    }

    if (collegueFromRequest.getPseudo() == null || collegueFromRequest.getPseudo().trim().isEmpty()) {
      throw new CollegueNotFoundException("Could not found the collegue");
    }

    return collegueRepository.findByPseudo(collegueFromRequest.getPseudo()).orElseThrow(() -> new CollegueNotFoundException("The collegue could not be null"));
  }
  
  @SendTo("/votes/last")
  private List<Vote> lastVote(@RequestParam(value = "since", required = false) Integer voteId){
	  return voteRepository.findByIdGreaterThanOrderByIdDesc(voteId);
  }
}
