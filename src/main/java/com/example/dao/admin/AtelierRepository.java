package com.example.dao.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entites.Atelier;


public interface AtelierRepository extends JpaRepository<Atelier, Long>{
	@Query("select a from Atelier a where a.descriptionAtelier like :mc")
	public Page<Atelier> chercher(@Param("mc") String mc,Pageable pageable);
	
	@Query("select count(a) from Atelier a")
	public int nbrAteliers();

}
