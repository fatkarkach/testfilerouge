package com.example.backend_recrutement.model.Repository;

import com.example.backend_recrutement.model.entity.Emploi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface publicationRepository extends JpaRepository<Emploi, Integer> {
}
