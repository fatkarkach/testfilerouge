package com.example.backend_recrutement.AuthAgent;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationAgentRequest {
    private int idRecruteur;
    private String nomRecruteur;
    private String adresse;
    private String email;
    private String password;
    private String numTele;
    private String image;
}
