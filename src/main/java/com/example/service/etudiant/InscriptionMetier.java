package com.example.service.etudiant;

import com.example.dao.etudiant.InscriptionRepository;
import com.example.entites.Inscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InscriptionMetier {
	@Autowired
    private InscriptionRepository repo;
	
	public List<Inscription> inscriptions(){
		List<Inscription> inscriptions = new ArrayList<Inscription>();
		for(Inscription inscription : repo.findAll()) {
			inscriptions.add(inscription);
		}
	return inscriptions;
	}
	
	public List<Inscription> inscriptionById(Long id){
		List<Inscription> inscriptions = inscriptions();
		List<Inscription> inscriptionsEtud = new ArrayList<Inscription>();
		for(Inscription inscription : inscriptions) {
			if(inscription.getId_insc().getId_etudiant()==id)
				inscriptionsEtud.add(inscription);
		}
	return inscriptionsEtud;
	}

}
