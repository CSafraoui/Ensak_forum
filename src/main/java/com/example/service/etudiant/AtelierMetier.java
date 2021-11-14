package com.example.service.etudiant;

import com.example.dao.admin.AtelierRepository;
import com.example.entites.Atelier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AtelierMetier {
	@Autowired
    private AtelierRepository repo;
	
	public List<Atelier> ateliers(){
		List<Atelier> ateliers = new ArrayList<Atelier>();
		for(Atelier atelier : repo.findAll()) {
			System.out.println("atelier : "+atelier.getTitreAtelier());
			System.out.println("supervisor "+atelier.getSuperviseurAtelier().getNomSuper());
			ateliers.add(atelier);
		}
	return ateliers;
	}
	
	public Atelier getAtelierById(Long id) {
		Atelier atelier=repo.getOne(id);
		return atelier;
	}
}
