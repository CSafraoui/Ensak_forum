package com.example.dao.etudiant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entites.*;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	@Query("select count(a) from Reservation a")
	public int countReservation();
}
