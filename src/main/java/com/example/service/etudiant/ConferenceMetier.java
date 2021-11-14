package com.example.service.etudiant;

import com.example.dao.admin.ConferenceRepository;
import com.example.entites.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ConferenceMetier {
	@Autowired
    private ConferenceRepository repo;
	
	public List<Conference> conferences(){
		List<Conference> conferences = new ArrayList<Conference>();
		for(Conference conference : repo.findAll()) {
			System.out.println("conference : "+conference.getTitreConf());
			conferences.add(conference);
		}
	return conferences;
	}
	
	public Conference getConfById(Long id) {
		Conference conf=repo.getOne(id);
		return conf;
	}
}
