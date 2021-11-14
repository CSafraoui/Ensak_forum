package com.example.entites;

import java.io.Serializable;

import javax.persistence.Column;

public class InscriptionKey implements Serializable{

    @Column(name = "id_etudiant")
    Long id_etudiant;

    @Column(name = "id_atelier")
    Long id_atelier;

    public InscriptionKey() {
        super();
        // TODO Auto-generated constructor stub
    }

    public InscriptionKey(Long id_etudiant, Long id_atelier) {
        super();
        this.id_etudiant = id_etudiant;
        this.id_atelier = id_atelier;
    }

    public Long getId_etudiant() {
        return id_etudiant;
    }

    public void setId_etudiant(Long id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

    public Long getId_atelier() {
        return id_atelier;
    }

    public void setId_atelier(Long id_atelier) {
        this.id_atelier = id_atelier;
    }


}
