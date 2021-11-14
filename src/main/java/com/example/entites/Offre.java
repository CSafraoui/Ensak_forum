package com.example.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Offre implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_offre;
	private String titre_offre;
	private String description;
	private Date date_pub;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name="REPRESENTANT")
	private Representant rep;

	public Offre() {
		super();
	}

	public Offre(String titre_offre, String description, Date date_pub, Representant rep) {
		super();
		this.titre_offre = titre_offre;
		this.description = description;
		this.date_pub = date_pub;
		this.rep = rep;
	}

	public Long getId_offre() {
		return id_offre;
	}

	public void setId_offre(Long id_offre) {
		this.id_offre = id_offre;
	}

	public String getTitre_offre() {
		return titre_offre;
	}

	public void setTitre_offre(String titre_offre) {
		this.titre_offre = titre_offre;
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

	public Representant getRep() {
		return rep;
	}

	public void setRep(Representant rep) {
		this.rep = rep;
	}



}
