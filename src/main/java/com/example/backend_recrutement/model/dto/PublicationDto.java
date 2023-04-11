package com.example.backend_recrutement.model.dto;

import com.example.backend_recrutement.model.entity.Recruteur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDto {
    private int idEmploi;
    private String titre;
    private String description;
    private String profil;
    private String ville;
    private String niveauEtude;
    private int salaire;
    private String facultative;
    private String status;

    private int id_recruteur;

/*
    public PublicationDto toPublicationDTO(PublicationDto publication){
        return PublicationDto.builder()
                .idEmploi(publication.getIdEmploi())
                .titre(publication.getTitre())
                .description(publication.getDescription())
                .profil(publication.getProfil())
                .ville(publication.getVille())
                .niveauEtude(publication.getNiveauEtude())
                .salaire(publication.getSalaire())
                .facultative(publication.getFacultative())
                .status(publication.getStatus())
                .id_recruteur(publication.getId_recruteur())
                .build();
    }
*/

}
