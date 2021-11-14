package com.example.service.etudiant;

import com.example.dao.etudiant.ReservationRepository;
import com.example.entites.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReservationMetier {
	@Autowired
    private ReservationRepository repo;
	
	public List<Reservation> reservations(){
		List<Reservation> reservations = new ArrayList<Reservation>();
		for(Reservation reservation : repo.findAll()) {
			System.out.println("reservation id : "+reservation.getId_reservation().getId_etudiant()+"  id conf : "+reservation.getId_reservation().getId_conf());
			reservations.add(reservation);
		}
	return reservations;
	}
	
	public List<Reservation> reservationsById(Long id){
		List<Reservation> reservations = reservations();
		List<Reservation> reservationsEtud = new ArrayList<Reservation>();
		for(Reservation reservation : reservations) {
			if(reservation.getId_reservation().getId_etudiant()==id)
				reservationsEtud.add(reservation);
		}
	return reservationsEtud;
	}


	public List<Reservation> reservationsConfById(Long id){
		List<Reservation> reservations = reservations();
		List<Reservation> reservationsConf = new ArrayList<Reservation>();
		for(Reservation reservation : reservations) {
			if(reservation.getId_reservation().getId_conf()==id)
				reservationsConf.add(reservation);
		}
		return reservationsConf;
	}
}
