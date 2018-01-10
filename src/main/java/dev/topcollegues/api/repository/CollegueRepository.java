package dev.topcollegues.api.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.topcollegues.api.entite.Collegue;

public interface CollegueRepository extends JpaRepository<Collegue, Integer> {

	Optional<Collegue> findByPseudo(String pseudo);
}
