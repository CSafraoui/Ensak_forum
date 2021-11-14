package com.example.service.representant;

import com.example.dao.admin.RepresentantRepository;
import com.example.entites.Representant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RepresentantService {
	@Autowired
    private RepresentantRepository repo;
	
	public Representant getRepresentantById(Long id) {
		Representant rep=repo.getOne(id);
		return rep;
	}
	

}
