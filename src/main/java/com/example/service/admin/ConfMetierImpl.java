package com.example.service.admin;


import com.example.dao.admin.ConferenceRepository;
import com.example.entites.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ConfMetierImpl implements IConfMetier {
	@Autowired
	ConferenceRepository confRep;
	@Override
	public Conference saveConference(Conference c) {
		return confRep.save(c);
	}

	@Override
	public Conference editConference(Long idConference) {
		return confRep.getOne(idConference);
	}

	@Override
	public void deleteConference(Long idConference) {
		confRep.deleteById(idConference);
		
	}
	public Page <Conference> chercher(String mc,int page,int sise){
		return confRep.chercher("%"+mc+"%", PageRequest.of(page, sise));
		}
	
	}
