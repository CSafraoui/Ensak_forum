package com.example.entites;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import com.example.entites.User;

@Entity
public class Profil implements Serializable{
	@Id
	private int idprofil;
	private String profil;

	@OneToMany(mappedBy = "profil", fetch=FetchType.EAGER,cascade = CascadeType.REFRESH)
	private Collection<User> listUsers;

	public Profil() {
		super();
	}

	public Profil(int profil) {
		super();
		this.idprofil = profil;
	}


	public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
	}

	public int getIdprofil() {
		return idprofil;
	}

	public void setIdprofil(int idprofil) {
		this.idprofil = idprofil;
	}



}
