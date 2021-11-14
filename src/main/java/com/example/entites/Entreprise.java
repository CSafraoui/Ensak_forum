package com.example.entites;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Entreprise implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEntreprise;
	private String nomE;
	private String adresseE;
	private String emailE;
	private String activiteE;
	private String logoE;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Representant representant;

	public Entreprise() {
		super();
	}

	public Entreprise(String nomE, String adresseE, String emailE, String activiteE,String logoE,Representant representant) {
		super();
		this.nomE = nomE;
		this.adresseE = adresseE;
		this.emailE = emailE;
		this.activiteE = activiteE;
		this.logoE = logoE;
		this.representant=representant;
	}



	public Long getIdEntreprise() {
		return idEntreprise;
	}

	public void setIdEntreprise(Long idEntreprise) {
		this.idEntreprise = idEntreprise;
	}

	public String getNomE() {
		return nomE;
	}

	public void setNomE(String nomE) {
		this.nomE = nomE;
	}

	public String getAdresseE() {
		return adresseE;
	}

	public void setAdresseE(String adresseE) {
		this.adresseE = adresseE;
	}

	public String getEmailE() {
		return emailE;
	}

	public void setEmailE(String emailE) {
		this.emailE = emailE;
	}

	public String getActiviteE() {
		return activiteE;
	}

	public void setActiviteE(String activiteE) {
		this.activiteE = activiteE;
	}

	public String getLogoE() {
		return logoE;
	}

	public void setLogoE(String logoE) {
		this.logoE = logoE;
	}

	public Representant getRepresentant() {
		return representant;
	}

	public void setRepresentant(Representant representant) {
		this.representant = representant;
	}



}
