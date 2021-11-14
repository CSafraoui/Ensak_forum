package com.example.entites;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Sponsor implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSponsor;
	@Size(min=4,max=30)
	private String nomSponsor;
	@Size(min=4,max=100)
	private String aproposSponsor;
	private String logoSponsor;
	public Sponsor() {
		super();
	}


	public Sponsor(String nomSpomsor, String aproposSponsor, String logoSponsor) {
		super();
		this.nomSponsor = nomSpomsor;
		this.aproposSponsor = aproposSponsor;
		this.logoSponsor = logoSponsor;
	}


	public Long getIdSponsor() {
		return idSponsor;
	}
	public void setIdSponsor(Long idSponsor) {
		this.idSponsor = idSponsor;
	}
	public String getNomSponsor() {
		return nomSponsor;
	}
	public void setNomSponsor(String nomSpomsor) {
		this.nomSponsor = nomSpomsor;
	}
	public String getAproposSponsor() {
		return aproposSponsor;
	}
	public void setAproposSponsor(String aproposSponsor) {
		this.aproposSponsor = aproposSponsor;
	}
	public String getLogoSponsor() {
		return logoSponsor;
	}
	public void setLogoSponsor(String logoSponsor) {
		this.logoSponsor = logoSponsor;
	}


}
