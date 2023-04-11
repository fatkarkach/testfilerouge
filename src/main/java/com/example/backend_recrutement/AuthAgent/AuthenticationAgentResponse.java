package com.example.backend_recrutement.AuthAgent;

import com.example.backend_recrutement.model.entity.Emploi;
import com.example.backend_recrutement.model.entity.Recruteur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationAgentResponse {
    private String token;
    private boolean recruteur_deja_kayn;
    private Integer id;
    private List<Emploi> myemploi;
    private String nom;
    private String image;
}
