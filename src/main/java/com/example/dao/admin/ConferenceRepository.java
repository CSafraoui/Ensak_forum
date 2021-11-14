package com.example.dao.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entites.Conference;
import com.example.entites.Superviseur;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference,Long>{
	@Query("select c from Conference c where c.titreConf like :mc or c.descriptionConf like :mc")
	public Page<Conference> chercher(@Param("mc") String mc,Pageable pageable);
	
	@Query("select count(c) from Conference c")
	public int nbrConferences();
}
