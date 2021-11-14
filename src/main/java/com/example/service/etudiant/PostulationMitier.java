package com.example.service.etudiant;

import com.example.dao.etudiant.PostulationRepository;
import com.example.entites.Postulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PostulationMitier {
	@Autowired
    private PostulationRepository repo;
	
	public List<Postulation> postulations(){
		List<Postulation> postulations = new ArrayList<Postulation>();
		for(Postulation postulation : repo.findAll()) {
			postulations.add(postulation);
		}
	return postulations;
	}
	
	public List<Postulation> postulationsById(Long id){
		List<Postulation> postulations = postulations();
		List<Postulation> postulationsEtud = new ArrayList<Postulation>();
		for(Postulation postulation : postulations) {
			if(postulation.getId_postulation().getId_etudiant()==id)
				postulationsEtud.add(postulation);
		}
	return postulationsEtud;
	}

	public Postulation postulationsByIdetudiantoffre(Long idEtudient,Long idOffre){
		List<Postulation> postulations = repo.findAll();
		Postulation p = new Postulation();


		for(Postulation postulation : postulations) {
			if(postulation.getId_postulation().getId_etudiant()==idEtudient && postulation.getId_postulation().getId_offre()==idOffre)
				p=postulation;
		}
		return p;
	}
	

}
