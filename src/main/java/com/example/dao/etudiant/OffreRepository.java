package com.example.dao.etudiant;

import com.example.entites.Offre;
import com.example.entites.Representant;
import com.example.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface OffreRepository extends JpaRepository<Offre, Long> {
	

    @Query(value = "SELECT count(id) FROM Offre")
    int countOffre();

    @Query("select o from Offre o where o.id_offre like :idOffre")
    Offre findOffreById(Long idOffre);

    @Query("select o from Offre o where o.titre_offre like :TitreOffre and o.rep like :representant")
    Collection<Offre> findOffreByTitre(String TitreOffre, Representant representant);


/*
    @Query("select o from Offre o where o.rep like :chars")
    List<Offre> findOffres(Long chars);*/

    @Query("select c from User c where c.login like %?1")
    User findRepresantantByName(String username);

}
