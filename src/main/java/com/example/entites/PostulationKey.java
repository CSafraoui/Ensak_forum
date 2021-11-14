package com.example.entites;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

public class PostulationKey implements Serializable{

    @Column(name = "id_etudiant")
    Long id_etudiant;

    @Column(name = "id_offre")
    Long id_offre;

    public PostulationKey() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PostulationKey(Long id_etudiant, Long id_offre) {
        super();
        this.id_etudiant = id_etudiant;
        this.id_offre = id_offre;
    }

    public Long getId_etudiant() {
        return id_etudiant;
    }

    public void setId_etudiant(Long id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

    public Long getId_offre() {
        return id_offre;
    }

    public void setId_offre(Long id_offre) {
        this.id_offre = id_offre;
    }


}
