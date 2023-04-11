package com.example.backend_recrutement.AuthAgent;

import com.example.backend_recrutement.config.JwtService;
import com.example.backend_recrutement.model.Repository.RecruteurRepository;
import com.example.backend_recrutement.model.entity.Emploi;
import com.example.backend_recrutement.model.entity.Recruteur;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationAgentService {
    private final RecruteurRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationAgentResponse registerRec(RegisterAgentRequest request) {

        Optional<Recruteur> op_user = repository.findByEmail(request.getEmail());
        if (!op_user.isPresent()) {
            var user = Recruteur.builder()
                    .nomRecruteur(request.getNomRecruteur())
                    .adresse(request.getAdresse())
                    .numTele(request.getNumTele())
                    .image(request.getImage())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationAgentResponse.builder()
                    .token(jwtToken)
                    .recruteur_deja_kayn(false)
                    .id(user.getIdRecruteur())
                    .nom(user.getNomRecruteur())
                    .image(null)
                    .myemploi(null)
                    .build();
        }else
            return AuthenticationAgentResponse.builder()
                    .token(null)
                    .recruteur_deja_kayn(true)
                    .id(null)
                    .nom(null)
                    .image(null)
                    .myemploi(null)
                    .build();
    }
    //login
    public AuthenticationAgentResponse loginRec(AuthenticationAgentRequest request) {

        try{
             authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        Optional<Recruteur> op_user = repository.findByEmail(request.getEmail());
        if (op_user.isPresent()) {
            var user = repository.findByEmail(request.getEmail()).orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationAgentResponse.builder()
                    .token(jwtToken)
                    .recruteur_deja_kayn(true)
                    .id(user.getIdRecruteur())
                    .nom(user.getNomRecruteur())
                    .image(user.getImage())
                    .myemploi((List<Emploi>) user.getEmploisByIdRecruteur())
                    .build();
        }else
            return AuthenticationAgentResponse.builder()
                    .token(null)
                    .recruteur_deja_kayn(false)
                    .nom(null)
                    .image(null)
                    .myemploi(null)
                    .build();

    }


}
