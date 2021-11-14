package com.example.service.representant;


import com.example.entites.Atelier;
import com.example.entites.Offre;
import com.example.entites.User;

public interface IOffreService {

    Offre findOffreById(Long idOffre);

    User findRepresantantByName(String username);


    public Offre saveOffre(Offre o);

}
