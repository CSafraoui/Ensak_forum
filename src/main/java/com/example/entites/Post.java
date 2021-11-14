package com.example.entites;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {
	
    @Id @GeneratedValue(generator="system-uuid")
    @Column(length = 64)
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id_post;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name="ETUDIANT")
    private Etudiant etudiant;

    private String answer;
    private String titre_post;
    private String description;
    private Date date_pub;
    private String sujet;

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public Post() {
    }

    public String getId_post() {
        return id_post;
    }

    public void setId_post(String id_post) {
        this.id_post = id_post;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTitre_post() {
        return titre_post;
    }

    public void setTitre_post(String titre_post) {
        this.titre_post = titre_post;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_pub() {
        return date_pub;
    }

    public void setDate_pub(Date date_pub) {
        this.date_pub = date_pub;
    }
}
