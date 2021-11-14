package com.example.entites;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Representant extends User implements Serializable{

	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
	private Entreprise entreprise;

	@OneToMany(mappedBy = "rep",fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
	private Collection<Offre> offres;

	public Representant() {
		super();
	}

	public Representant(Entreprise entreprise, Collection<Offre> offres) {
		super();
		this.entreprise = entreprise;
		this.offres = offres;
	}

	public Representant(String login, String password, String nom, String prenom, Profil profil, Entreprise entreprise) {
		// TODO Auto-generated constructor stub
		super(login,password,nom,prenom,profil);
		this.entreprise=entreprise;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public Collection<Offre> getOffres() {
		return offres;
	}

	public void setOffres(Collection<Offre> offres) {
		this.offres = offres;
	}



}
