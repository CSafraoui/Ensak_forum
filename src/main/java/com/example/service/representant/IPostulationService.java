package com.example.service.representant;

import com.example.entites.Postulation;
import com.example.entites.PostulationKey;

public interface IPostulationService {
    Postulation findPostulationkey(PostulationKey postulationKey);
    PostulationKey findPostulation(Long idEtudiant, Long idOffre);
}
