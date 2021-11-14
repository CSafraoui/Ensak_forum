package com.example.entites;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Atelier implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAtelier;
	private String titreAtelier;
	private String descriptionAtelier;
	private String horaireAtelier;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)

	private Superviseur superviseurAtelier;

	public Atelier() {
		super();
	}

	public Atelier(String titreAtelier, String descriptionAtelier, String horaireAtelier, Superviseur superviseurA) {
		super();
		this.titreAtelier = titreAtelier;
		this.descriptionAtelier = descriptionAtelier;
		this.horaireAtelier = horaireAtelier;
		this.superviseurAtelier = superviseurA;
	}

	public Long getIdAtelier() {
		return idAtelier;
	}

	public void setIdAtelier(Long idAtelier) {
		this.idAtelier = idAtelier;
	}

	public String getTitreAtelier() {
		return titreAtelier;
	}

	public void setTitreAtelier(String titreAtelier) {
		this.titreAtelier = titreAtelier;
	}

	public String getDescriptionAtelier() {
		return descriptionAtelier;
	}

	public void setDescriptionAtelier(String descriptionAtelier) {
		this.descriptionAtelier = descriptionAtelier;
	}

	public String getHoraireAtelier() {
		return horaireAtelier;
	}

	public void setHoraireAtelier(String horaireAtelier) {
		this.horaireAtelier = horaireAtelier;
	}

	public Superviseur getSuperviseurAtelier() {
		return superviseurAtelier;
	}

	public void setSuperviseurAtelier(Superviseur superviseurA) {
		this.superviseurAtelier = superviseurA;
	}


}