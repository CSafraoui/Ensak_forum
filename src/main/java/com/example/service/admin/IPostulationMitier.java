package com.example.service.admin;

import com.example.entites.Postulation;

import java.util.Collection;

public interface IPostulationMitier {
    Collection<Postulation> findPostulationByIdOffre(Long id);
}
