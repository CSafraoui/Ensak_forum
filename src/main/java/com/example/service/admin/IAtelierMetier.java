package com.example.service.admin;

import com.example.entites.Atelier;
import org.springframework.data.domain.Page;


public interface IAtelierMetier {
	public Atelier saveAtelier(Atelier c);
	public Atelier editAtelier(Long idAtelier);
	public void deleteAtelier(Long idAtelier);
	public Page <Atelier> chercher(String mc,int page,int sise);
	
	

}
