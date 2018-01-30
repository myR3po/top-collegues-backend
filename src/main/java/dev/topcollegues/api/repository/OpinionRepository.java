package dev.topcollegues.api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.topcollegues.api.entite.Collegue;
import dev.topcollegues.api.entite.Opinion;

public interface OpinionRepository extends JpaRepository<Opinion, Integer> {
	List<Opinion> findByCollegue(Collegue collegue);
}
