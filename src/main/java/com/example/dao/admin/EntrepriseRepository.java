package com.example.dao.admin;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entites.Entreprise;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long>{
	@Query("select e from Entreprise e where e.nomE like :mc")
	public Page <Entreprise> chercherByNom(@Param("mc") String mc,Pageable pageable);
	
	@Query("select e from Entreprise e where e.activiteE like :mc")
	public Page <Entreprise> chercherByActivite(@Param("mc") String mc,Pageable pageable);
	
	@Query("select e from Entreprise e where e.adresseE like :mc")
	public Page <Entreprise> chercherByVille(@Param("mc") String mc,Pageable pageable);
	
	@Query("select count(e) from Entreprise e")
	public int nbrEtr();
	

}
