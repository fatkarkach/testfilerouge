package com.example.backend_recrutement.model.dto;
import com.example.backend_recrutement.model.entity.Emploi;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruteurDTO {
    private int idRecruteur;
    private String nomRecruteur;
    private String adresse;
    private String email;
    private String password;
    private String numTele;
    private String image;
    private int usersByIdUser;}
