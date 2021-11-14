package com.example.dao.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entites.Representant;


@Repository
public interface RepresentantRepository extends JpaRepository<Representant, Long> {
	@Query("select count(e) from Representant e")
	public int countRep();
	
    @Query("select r from Representant as r where r.id_user Like :idRepresentant")
    Representant findRepresentantById(Long idRepresentant);

}
