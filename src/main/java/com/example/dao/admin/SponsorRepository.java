package com.example.dao.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entites.Sponsor;
import com.example.entites.Superviseur;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Long>{
	@Query("select s from Sponsor s where s.nomSponsor like :mc or s.aproposSponsor like :mc")
	public Page<Sponsor> chercher(@Param("mc") String mc,Pageable pageable);
	
	@Query("select count(a) from Sponsor a")
	public int countSponsor();
}
