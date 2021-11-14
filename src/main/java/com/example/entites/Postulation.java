package com.example.entites;

import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Postulation implements Serializable {
    @EmbeddedId
    PostulationKey id_postulation;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @MapsId("id_etudiant")
    @JoinColumn(name = "id_etudiant")
    Etudiant etudiant;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @MapsId("id_offre")
    @JoinColumn(name = "id_offre")
    Offre offre;

    Date date_postulation;

    public Postulation() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Postulation(Long id_etudiant, Long id_offre, Etudiant etudiant, Offre offre, Date date_postulation) {
        super();
        this.id_postulation = new PostulationKey(id_etudiant, id_offre);
        this.etudiant = etudiant;
        this.offre = offre;
        this.date_postulation = date_postulation;
    }

    public PostulationKey getId_postulation() {
        return id_postulation;
    }

    public void setId_postulation(PostulationKey id_postulation) {
        this.id_postulation = id_postulation;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public Date getDate_postulation() {
        return date_postulation;
    }

    public void setDate_postulation(Date date_postulation) {
        this.date_postulation = date_postulation;
    }

    @Override
    public String toString() {
        return "Postulation [id_postulation=" + id_postulation + ", etudiant=" + etudiant + ", offre=" + offre
                + ", date_postulation=" + date_postulation + "]";
    }


}

