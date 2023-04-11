package com.example.backend_recrutement.Authentication;
import com.example.backend_recrutement.model.Repository.UserRepository;
import com.example.backend_recrutement.config.JwtService;
import com.example.backend_recrutement.model.entity.Agent;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {

        Optional<Agent> op_user = repository.findByEmail(request.getEmail());
        if (!op_user.isPresent()) {
            var user = Agent.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .user_deja_kayn(false)
                    .build();
        }else
            return AuthenticationResponse.builder()
                    .token(null)
                    .user_deja_kayn(true)
                    .build();
    }
    //login
    public AuthenticationResponse login(AuthenticationRequest request) {

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        Optional<Agent> op_user = repository.findByEmail(request.getEmail());
        if (op_user.isPresent()) {
            var user = repository.findByEmail(request.getEmail()).orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .user_deja_kayn(true)
                    .build();
        }else
            return AuthenticationResponse.builder()
                    .token(null)
                    .user_deja_kayn(false)
                    .build();

    }


}
