package com.example.backend_recrutement.Controller;

import com.example.backend_recrutement.AuthAgent.AuthenticationAgentRequest;
import com.example.backend_recrutement.AuthAgent.AuthenticationAgentResponse;
import com.example.backend_recrutement.Service.EmploiService;
import com.example.backend_recrutement.Service.RecruteurService;
import com.example.backend_recrutement.model.Repository.RecruteurRepository;
import com.example.backend_recrutement.model.Repository.publicationRepository;
import com.example.backend_recrutement.model.dto.PublicationDto;
import com.example.backend_recrutement.model.dto.RecruteurDTO;
import com.example.backend_recrutement.model.entity.Emploi;
import com.example.backend_recrutement.model.entity.Recruteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/faho")
public class HomeController {
    @Autowired
   private  EmploiService emploiService;
    @Autowired
    private  RecruteurService recruteurService;
    @Autowired
    private publicationRepository publicationRepository;
    @Autowired
    private RecruteurRepository recruteurRepository;
 @CrossOrigin(origins = "http://localhost:4200")
 @PostMapping("/auth/save")
 @ResponseBody
 public PublicationDto testpost( @RequestBody PublicationDto test){
     PublicationDto st=test;

     Optional<Recruteur> recruteur= recruteurService.getrec(test.getId_recruteur());
     if(recruteur.isPresent()){
         Emploi emploi=new Emploi();
         emploi.setDescription(st.getDescription());
         emploi.setFacultative(st.getFacultative());
         emploi.setVille(st.getVille());
         emploi.setNiveauEtude(st.getNiveauEtude());
         emploi.setProfil((st.getProfil()));
         emploi.setSalaire(st.getSalaire());
         emploi.setStatus(st.getStatus());
         emploi.setTitre(st.getTitre());
         emploi.setRecruteurByIdRecruteur(recruteur.get());
         emploiService.saveemploi(emploi);
         System.out.println("emploi tzadt bravou ");
     }
     return test;
    }
    //get my les offes
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/auth/getmaoffre")
    @ResponseBody
    public List<PublicationDto> getmaoffre() {
        return  emploiService.getoffre_all();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/auth/getoffres")
    @ResponseBody
    List<Recruteur> all() {
        return recruteurRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/auth/get_image/{filename}")
    public Resource getImage(@PathVariable String filename){
        return recruteurService.getImageRecruteur(filename);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/auth/getmaoffer/{id_rec}")
    public ResponseEntity<AuthenticationAgentResponse> getmaoffera(@PathVariable int id_rec) {
        Optional<Recruteur> recruteur = recruteurRepository.findById(id_rec);
        AuthenticationAgentResponse aaa = null;
        if (recruteur.isPresent()) {
            aaa = AuthenticationAgentResponse.builder()
                    .token(null)
                    .recruteur_deja_kayn(true)
                    .id(recruteur.get().getIdRecruteur())
                    .nom(recruteur.get().getNomRecruteur())
                    .image(recruteur.get().getImage())
                    .myemploi((List<Emploi>) recruteur.get().getEmploisByIdRecruteur())
                    .build();
        }
        return ResponseEntity.ok(aaa);
    }
}

