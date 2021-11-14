package com.example.dao.etudiant;

import com.example.entites.Offre;
import com.example.entites.Postulation;
import com.example.entites.PostulationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface PostulationRepository extends JpaRepository<Postulation, Long> {
    @Query("select p from Postulation as p where p.id_postulation.id_offre Like :id")
    Collection<Postulation> findPostulationByIdOffre(Long id);

	@Query("select count(a) from Postulation a")
	public int countPostulation();

    /*
    @Query("select p from Postulation as p where p.id_postulation Like :postulationkey")
    Postulation findPostulationkey(PostulationKey postulationKey);

    @Query("select p from PostulationKey as p where p.id_etudiant Like :idEtudiant and p.id_offre Like :idOffre")
    PostulationKey findPostulation(Long idEtudiant, Long idOffre);
    */



}
