package com.example.dao.etudiant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entites.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
	
	@Query("select count(a) from Etudiant a")
	public int countEtudiants();
	
}
