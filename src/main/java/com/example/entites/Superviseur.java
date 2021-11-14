package com.example.entites;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Superviseur implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_super;
	private String nomSuper;
	private String prenomSuper;
	private String aproposSuper;

	@OneToMany(mappedBy = "superviseurConf",fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
	private Collection<Conference> conferences;

	@OneToMany(mappedBy = "superviseurAtelier",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	private Collection<Atelier> ateliers;


	public Superviseur() {
		super();
	}
	public Superviseur(String nomSuper, String prenomSuper, String aproposSuper, Collection<Conference> conferences,
					   Collection<Atelier> ateliers) {
		super();
		this.nomSuper = nomSuper;
		this.prenomSuper = prenomSuper;
		this.aproposSuper = aproposSuper;
		this.conferences = conferences;
		this.ateliers = ateliers;
	}
	public Long getId_super() {
		return id_super;
	}
	public void setId_super(Long id_super) {
		this.id_super = id_super;
	}
	public String getNomSuper() {
		return nomSuper;
	}
	public void setNomSuper(String nomSuper) {
		this.nomSuper = nomSuper;
	}
	public String getPrenomSuper() {
		return prenomSuper;
	}
	public void setPrenomSuper(String prenomSuper) {
		this.prenomSuper = prenomSuper;
	}
	public String getAproposSuper() {
		return aproposSuper;
	}
	public void setAproposSuper(String aproposSuper) {
		this.aproposSuper = aproposSuper;
	}
	public Collection<Conference> getConferences() {
		return conferences;
	}
	public void setConferences(Collection<Conference> conferences) {
		this.conferences = conferences;
	}
	public Collection<Atelier> getAteliers() {
		return ateliers;
	}
	public void setAteliers(Collection<Atelier> ateliers) {
		this.ateliers = ateliers;
	}



}
