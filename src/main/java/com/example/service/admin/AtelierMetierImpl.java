package com.example.service.admin;


import com.example.dao.admin.AtelierRepository;
import com.example.entites.Atelier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AtelierMetierImpl implements IAtelierMetier {
	@Autowired
	AtelierRepository atelierRep;
	
	@Override
	public Atelier saveAtelier(Atelier c) {
		return atelierRep.save(c);
	}

	@Override
	public Atelier editAtelier(Long idAtelier) {
		return atelierRep.getOne(idAtelier);
	}

	@Override
	public void deleteAtelier(Long idAtelier) {
		atelierRep.deleteById(idAtelier);
		
	}
	public Page <Atelier> chercher(String mc,int page,int sise){
		return atelierRep.chercher("%"+mc+"%", PageRequest.of(page, sise));
		}
	
	}
