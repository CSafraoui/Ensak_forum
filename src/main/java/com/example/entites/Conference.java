package com.example.entites;

import java.io.Serializable;
import java.sql.Time;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Conference implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idConf;
	private String titreConf;
	private String descriptionConf;
	private String horaireConf;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Superviseur superviseurConf;


	public Conference() {
		super();
	}

	public Conference(String titreConf, String descriptionConf, String horaireConf, Superviseur superviseurConf) {
		super();
		this.titreConf = titreConf;
		this.descriptionConf = descriptionConf;
		this.horaireConf = horaireConf;
		this.superviseurConf = superviseurConf;
	}

	public Long getIdConf() {
		return idConf;
	}

	public void setIdConf(Long idConf) {
		this.idConf = idConf;
	}

	public String getTitreConf() {
		return titreConf;
	}

	public void setTitreConf(String titreConf) {
		this.titreConf = titreConf;
	}

	public String getDescriptionConf() {
		return descriptionConf;
	}

	public void setDescriptionConf(String descriptionConf) {
		this.descriptionConf = descriptionConf;
	}

	public String getHoraireConf() {
		return horaireConf;
	}

	public void setHoraireConf(String horaireConf) {
		this.horaireConf = horaireConf;
	}

	public Superviseur getSuperviseurConf() {
		return superviseurConf;
	}

	public void setSuperviseurConf(Superviseur superviseurConf) {
		this.superviseurConf = superviseurConf;
	}




}
