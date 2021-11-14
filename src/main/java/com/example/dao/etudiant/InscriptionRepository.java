package com.example.dao.etudiant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entites.*;

public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
	@Query("select count(a) from Inscription a")
	public int countInsc();
}
