package com.example.service.admin;


import com.example.dao.admin.EntrepriseRepository;
import com.example.entites.Entreprise;
import com.example.entites.Representant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class EntrepriseMetierImpl implements IEntrepriseMetier {
	@Autowired
	EntrepriseRepository entrepriseRepository;

	@Override
	public Entreprise saveEntreprise(Entreprise e) {
		return entrepriseRepository.save(e);
	}


	@Override
	public void deleteEntreprise(Long idEntreprise) {
		entrepriseRepository.deleteById(idEntreprise);
		
	}


	@Override
	public Entreprise editEntreprise(Long idEntreprise) {
		return entrepriseRepository.getOne(idEntreprise);
	}
	@Override
	public Entreprise ajouterEntreprise(String nom, String adresse, String acrivite, String email, MultipartFile logo) {
		Entreprise e=new Entreprise();
		e.setNomE(nom);
		e.setAdresseE(adresse);
		e.setActiviteE(acrivite);
		e.setEmailE(email);


		
		Path path=Paths.get("C:\\Users\\Administrateur\\Documents\\workspace-spring-tool-suite-4-4.9.0.RELEASE\\forum\\src\\main\\resources\\static\\");

		try {
			String fileName=logo.getOriginalFilename();
			InputStream inputStream =logo.getInputStream();
			Files.copy(inputStream, path.resolve(fileName),StandardCopyOption.REPLACE_EXISTING);
			e.setLogoE(fileName.toLowerCase());
		} catch (Exception e2) {
			System.out.println("upload exeption");
		}
		return e;
		
	}
   
	
}
