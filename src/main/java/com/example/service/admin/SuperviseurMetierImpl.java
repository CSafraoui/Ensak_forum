package com.example.service.admin;


import com.example.dao.admin.SuperviseurRepository;
import com.example.entites.Superviseur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SuperviseurMetierImpl implements ISuperviseurMetier {
	@Autowired
	SuperviseurRepository superviseurRepository;

	@Override
	public Superviseur saveSuperviseur(Superviseur e) {
		return superviseurRepository.save(e);
	}

	@Override
	public Page<Superviseur> chercher(String mc, int page,int size) {
		return superviseurRepository.chercher("%"+mc+"%",PageRequest.of(page,size));
	}

	@Override
	public void deleteSuperviseur(Long idSuperviseur) {
		superviseurRepository.deleteById(idSuperviseur);
		
	}

	@Override
	public Superviseur editSuperviseur(Long idSuperviseur) {
		return superviseurRepository.getOne(idSuperviseur);
	}

	
}
