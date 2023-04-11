package com.example.backend_recrutement.model.Repository;
import com.example.backend_recrutement.model.entity.Recruteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruteurRepository extends JpaRepository<Recruteur, Integer> {
    Optional<Recruteur> findByEmail(String email);
}
