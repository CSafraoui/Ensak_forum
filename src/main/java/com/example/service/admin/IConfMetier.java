package com.example.service.admin;

import com.example.entites.Conference;
import org.springframework.data.domain.Page;


public interface IConfMetier {
	public Conference saveConference(Conference c);
	public Conference editConference(Long idConference);
	public void deleteConference(Long idConference);
	public Page <Conference> chercher(String mc,int page,int sise);
	
	

}
