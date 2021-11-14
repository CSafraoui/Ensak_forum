package com.example.dao.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entites.Entreprise;
import com.example.entites.Superviseur;

@Repository
public interface SuperviseurRepository extends JpaRepository<Superviseur, Long>{
	@Query("select s from Superviseur s where s.nomSuper like :mc or s.prenomSuper like :mc or s.aproposSuper like :mc")
	public Page<Superviseur> chercher(@Param("mc") String mc,Pageable pageable);
	
	@Query("select count(a) from Superviseur a")
	public int countSuperviseur();
	
}
