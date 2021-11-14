package com.example.entites;

import java.io.Serializable;

import javax.persistence.Column;


public class ReservationKey implements Serializable{
    @Column(name = "id_etudiant")
    Long id_etudiant;

    @Column(name = "id_conf")
    Long id_conf;


    public ReservationKey(){
        super();
    }
    public ReservationKey(Long idEtudiant,Long idConf){
        this.id_etudiant=idEtudiant;
        this.id_conf=idConf;
    }
    public Long getId_etudiant() {
        return id_etudiant;
    }

    public void setId_etudiant(Long id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

    public Long getId_conf() {
        return id_conf;
    }

    public void setId_conf(Long id_conf) {
        this.id_conf = id_conf;
    }


}

