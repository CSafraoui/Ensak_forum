package com.example.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;


@Entity
public class Inscription implements Serializable{
    @EmbeddedId
    InscriptionKey id_insc;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @MapsId("id_etudiant")
    @JoinColumn(name = "id_etudiant")
    Etudiant etudiant;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @MapsId("id_atelier")
    @JoinColumn(name = "id_atelier")
    Atelier atelier;

    String date_Insc;

    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Inscription(Long id_etudiant, Long id_atelier, Etudiant etudiant, Atelier atelier, String date_Insc) {
        super();
        this.id_insc = new InscriptionKey(id_etudiant,id_atelier);
        this.etudiant = etudiant;
        this.atelier = atelier;
        this.date_Insc = date_Insc;
    }

    public InscriptionKey getId_insc() {
        return id_insc;
    }

    public void setId_insc(InscriptionKey id_insc) {
        this.id_insc = id_insc;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Atelier getAtelier() {
        return atelier;
    }

    public void setAtelier(Atelier atelier) {
        this.atelier = atelier;
    }

    public String getDate_Insc() {
        return date_Insc;
    }

    public void setDate_Insc(String date_Insc) {
        this.date_Insc = date_Insc;
    }

    @Override
    public String toString() {
        return "Inscription [id_insc=" + id_insc + ", etudiant=" + etudiant + ", atelier=" + atelier + ", date_Insc="
                + date_Insc + "]";
    }



}
