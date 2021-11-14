package com.example.service.etudiant;

import com.example.dao.etudiant.OffreRepository;
import com.example.entites.Atelier;
import com.example.entites.Offre;
import com.example.entites.User;
import com.example.service.representant.IOffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OffreMetier implements IOffreService {



	@Autowired
	private OffreRepository offreRepository;
	public Offre getOffreById(Long id) {
		Offre offre=offreRepository.getOne(id);
		return offre;
	}

	public List<Offre> offres(){
		List<Offre> offres = new ArrayList<Offre>();
		for(Offre offre : offreRepository.findAll()) {
			System.out.println("offre : "+offre.getTitre_offre());
			offres.add(offre);
		}
		return offres;
	}




	@Override
	public Offre findOffreById(Long idOffre) {
		return findOffreById(idOffre);
	}

	public void setOffre(Offre offre,String title,String description){
		offre.setTitre_offre(title);
		offre.setDescription(description);
	}

	public Offre saveOffre(Offre offre){
		return offreRepository.save(offre);
	}

	public void delete(Long id) {
		offreRepository.deleteById(id);
	}
	public Offre editOffre(Long idOffre) {
		return offreRepository.getOne(idOffre);
	}

	public int countOffre(){
		return offreRepository.countOffre();
	}




	@Override
	public User findRepresantantByName(String username) {
		return offreRepository.findRepresantantByName(username);
	}
}
