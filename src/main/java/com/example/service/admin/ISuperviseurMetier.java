package com.example.service.admin;

import com.example.entites.Superviseur;
import org.springframework.data.domain.Page;


public interface ISuperviseurMetier {
	public Superviseur saveSuperviseur(Superviseur s);
	public Page <Superviseur> chercher(String mc, int page,int size);
	public Superviseur editSuperviseur(Long idSuperviseur);
	void deleteSuperviseur(Long idSuperviseur);
	

}
