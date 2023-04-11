package com.example.backend_recrutement.AuthAgent;


import com.example.backend_recrutement.Service.RecruteurService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/faho/auth")
@RequiredArgsConstructor
public class AuthenticationAgentController {
    private final AuthenticationAgentService service;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private RecruteurService recruteurService;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/registerRecruteur")
    public AuthenticationAgentResponse registerRecruteur(
            @ModelAttribute RegisterAgentRequest request, @RequestParam("file") MultipartFile my_file
    ) {
        String name_image =null;
        RegisterAgentRequest registerRequest=request;
        Optional<MultipartFile> optionalMy_file = Optional.ofNullable(my_file);
        if (optionalMy_file.isPresent()) {
            if(recruteurService.test_type_file(optionalMy_file.get())){
                name_image=recruteurService.saveImageAnimaux(optionalMy_file.get());
                registerRequest.setImage(name_image);
                return service.registerRec(registerRequest);
            }
        }
        return AuthenticationAgentResponse.builder()
                .token(null)
                .recruteur_deja_kayn(false)
                .myemploi(null)
                .id(null)
                .build();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/loginRecruteur")
    public ResponseEntity<AuthenticationAgentResponse> authenticateRecruteur(@RequestBody AuthenticationAgentRequest request) {
            return ResponseEntity.ok(service.loginRec(request));

    }
}
