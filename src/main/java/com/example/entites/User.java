package com.example.entites;


import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public class User implements Serializable{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_user;
	private String login;
	private String password;
	private String nom;
	private String prenom;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "PROFIL")
	private Profil profil;

	public User() {
		super();
	}

	public User(String login, String password, String nom, String prenom, Profil profil) {
		super();
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.profil = profil;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", login=" + login + ", password=" + password + ", nom=" + nom
				+ ", prenom=" + prenom + ", profil=" + profil + "]";
	}


}




