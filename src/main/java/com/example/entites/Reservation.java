package com.example.entites;

import java.io.Serializable;

import javax.persistence.*;

@Entity

public class Reservation implements Serializable{

    @EmbeddedId
    ReservationKey id_reservation;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @MapsId("id_etudiant")
    @JoinColumn(name = "id_etudiant")
    Etudiant etudiant;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @MapsId("id_conf")
    @JoinColumn(name = "id_conf")
    Conference Conference;

    int num_place;

    public Reservation() {
        super();
    }
    public Reservation(Long idEtudiant,Long idConf,Etudiant etudiant, com.example.entites.Conference conference,int numPlace) {
        super();
        id_reservation=new ReservationKey(idEtudiant,idConf);
        this.etudiant = etudiant;
        Conference = conference;
        num_place=numPlace;
    }

    public ReservationKey getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(ReservationKey id_reservation) {
        this.id_reservation = id_reservation;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Conference getConference() {
        return Conference;
    }

    public void setConference(Conference conference) {
        Conference = conference;
    }

    public int getNum_place() {
        return num_place;
    }

    public void setNum_place(int num_place) {
        this.num_place = num_place;
    }

    @Override
    public String toString() {
        return "Reservation [id_reservation=" + id_reservation + ", etudiant=" + etudiant + ", Conference=" + Conference
                + ", num_place=" + num_place + "]";
    }




}