package com.example.backend_recrutement.model.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "emploi")
@Entity
public class Emploi  {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_emploi")
    private int idEmploi;
    @Basic
    @Column(name = "titre")
    private String titre;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "profil")
    private String profil;
    @Basic
    @Column(name = "ville")
    private String ville;
    @Basic
    @Column(name = "niveau_etude")
    private String niveauEtude;
    @Basic
    @Column(name = "salaire")
    private int salaire;
    @Basic
    @Column(name = "facultative")
    private String facultative;
    @Basic
    @Column(name = "status")
    private String status;
/*    @Basic
    @Column(name = "id_recruteur")
    private int idRecruteur;*/
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "id_recruteur", referencedColumnName = "id_recruteur", nullable = false)
    private Recruteur recruteurByIdRecruteur;

}
