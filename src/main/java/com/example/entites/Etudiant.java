package com.example.entites;
import java.util.Collection;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class Etudiant extends User {
	private String filiere;
	private String niveau;
	private String ecole;
	private String cv;
	private String img;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public Etudiant() {
		super();
	}

	public Etudiant(String login, String password, String nom, String prenom, Profil profil, String filiere,
					String niveau, String ecole) {
		super(login, password, nom, prenom, profil);
		this.filiere = filiere;
		this.niveau = niveau;
		this.ecole = ecole;
	}

	public String getFiliere() {
		return filiere;
	}

	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public String getEcole() {
		return ecole;
	}

	public void setEcole(String ecole) {
		this.ecole = ecole;
	}

	@Override
	public String toString() {
		return super.toString()+"Etudiant [filiere=" + filiere + ", niveau=" + niveau + ", ecole=" + ecole + ", cv=" + cv + ", img="
				+ img + "]";
	}

	




}
