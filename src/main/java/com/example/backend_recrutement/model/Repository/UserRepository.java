package com.example.backend_recrutement.model.Repository;
import com.example.backend_recrutement.model.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Agent, Integer> {
    Optional<Agent> findByEmail(String email);

}
