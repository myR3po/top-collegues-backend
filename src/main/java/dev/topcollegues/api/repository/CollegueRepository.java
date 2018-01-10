package dev.topcollegues.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import dev.topcollegues.api.entite.Collegue;

public interface CollegueRepository extends JpaRepository<Collegue, Integer> {

	Collegue findByPseudo(String pseudo);
}
