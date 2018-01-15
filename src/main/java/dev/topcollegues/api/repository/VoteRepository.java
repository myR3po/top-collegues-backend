package dev.topcollegues.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.topcollegues.api.entite.Vote;
import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

	List<Vote> findFirst3ByOrderByIdDesc();

	List<Vote> findByIdGreaterThanOrderByIdDesc(Integer id);
}
