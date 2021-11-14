package com.example.service.etudiant;

import com.example.dao.etudiant.EtudiantRepository;
import com.example.dao.etudiant.OffreRepository;
import com.example.entites.Etudiant;
import com.example.entites.Offre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EtudiantMetiersImp {
	@Autowired
	private OffreRepository offreRepository;
	@Autowired
	private  EtudiantRepository etudiantRepository;
	
	public Offre consulterOffres (Long idoffre) {
		Offre offre=offreRepository.getOne(idoffre);
		if(offre == null) throw new RuntimeException("offre introuvable");
		return offre;
	}
	
	public Etudiant GetEtudiantById(Long id) {
		Etudiant etudiant = null;
	 try {
		etudiant= etudiantRepository.getOne(id);
		}catch(Exception e) {
			System.out.println("e");
		}
	 return etudiant;
	}
	
}
