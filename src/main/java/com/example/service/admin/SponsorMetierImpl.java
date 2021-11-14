package com.example.service.admin;


import com.example.dao.admin.SponsorRepository;
import com.example.entites.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class SponsorMetierImpl implements ISponsorMetier {
	@Autowired
	SponsorRepository sponsorRepository;

	@Override
	public Sponsor saveSponsor(Sponsor s) {
		return sponsorRepository.save(s );
	}

	@Override
	public Page<Sponsor> chercher(String mc, int page, int size) {
		return sponsorRepository.chercher("%"+mc+"%",PageRequest.of(page,size));
	}

	@Override
	public Sponsor editSponsor(Long idSponsor) {
		return sponsorRepository.getOne(idSponsor);
	}

	@Override
	public void deleteSponsor(Long idSponsor) {
		sponsorRepository.deleteById(idSponsor);
	}

	@Override
	public Sponsor ajouterSponsor(String nom, String apropos, MultipartFile logo) {
		Sponsor sp =new Sponsor();
		sp.setNomSponsor(nom);
		sp.setAproposSponsor(apropos);
		
		Path path=Paths.get("C:\\Users\\Administrateur\\Documents\\workspace-spring-tool-suite-4-4.9.0.RELEASE\\forum\\src\\main\\resources\\static\\");

		try {
			String fileName=logo.getOriginalFilename();
			InputStream inputStream =logo.getInputStream();
			Files.copy(inputStream, path.resolve(fileName),StandardCopyOption.REPLACE_EXISTING);
			sp.setLogoSponsor(fileName.toLowerCase());
		} catch (Exception e2) {
			System.out.println("upload exeption");
		}
		return sp;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
