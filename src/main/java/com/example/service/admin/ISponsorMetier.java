package com.example.service.admin;

import com.example.entites.Sponsor;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;


public interface ISponsorMetier {
	public Sponsor saveSponsor(Sponsor s);
	public Page <Sponsor> chercher(String mc, int page,int size);
	public Sponsor editSponsor(Long idSponsor);
	void deleteSponsor(Long idSponsor);
	public Sponsor ajouterSponsor(String nom,String apropos,MultipartFile logo );
	

}
