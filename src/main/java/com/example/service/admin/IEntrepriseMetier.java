package com.example.service.admin;

import com.example.entites.Entreprise;
import com.example.entites.Representant;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface IEntrepriseMetier {
	public Entreprise saveEntreprise(Entreprise e);
	public Entreprise editEntreprise(Long idEntreprise);
	void deleteEntreprise(Long idEntreprise);


    Entreprise ajouterEntreprise(String nom, String activite, String adresse, String email, MultipartFile logo);


}
