package com.example.dao.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entites.Profil;

@Repository
public interface ProfilRepository extends JpaRepository<Profil, Integer>{

}
