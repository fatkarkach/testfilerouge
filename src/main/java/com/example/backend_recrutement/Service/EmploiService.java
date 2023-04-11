package com.example.backend_recrutement.Service;

import com.example.backend_recrutement.model.Repository.publicationRepository;
import com.example.backend_recrutement.model.dto.PublicationDto;
import com.example.backend_recrutement.model.dto.RecruteurDTO;
import com.example.backend_recrutement.model.entity.Emploi;
import com.example.backend_recrutement.model.entity.Recruteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class EmploiService {
    @Autowired
    private publicationRepository publicationRepository;
    //ajouter un emploi
    public void   saveemploi(Emploi emploi){
         publicationRepository.save(emploi);
    }
    //get tous les emplois

    public List<PublicationDto>  getoffre_all(){
/*
        return publicationRepository.findAll();
*/
        return ((List<Emploi>) publicationRepository
                .findAll())
                .stream()
                .map(this::convertDataIntoDTO)
                .collect(Collectors.toList());
    }
    private PublicationDto convertDataIntoDTO (Emploi animauxData) {

        // create instance of our UserLocationDTO class
        PublicationDto dto = new PublicationDto();
        dto.setIdEmploi(animauxData.getIdEmploi());
        dto.setTitre(animauxData.getTitre());
        dto.setDescription(animauxData.getDescription());
        dto.setProfil(animauxData.getProfil());
        dto.setVille(animauxData.getVille());
        dto.setNiveauEtude(animauxData.getNiveauEtude());
        dto.setSalaire(animauxData.getSalaire());
        dto.setFacultative(animauxData.getFacultative());
        dto.setStatus(animauxData.getStatus());
        dto.setId_recruteur(animauxData.getRecruteurByIdRecruteur().getIdRecruteur());
        return dto;
    }

}
