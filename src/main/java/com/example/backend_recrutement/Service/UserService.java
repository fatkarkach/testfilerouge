package com.example.backend_recrutement.Service;
import com.example.backend_recrutement.model.Repository.UserRepository;
import com.example.backend_recrutement.model.dto.UserDTO;
import com.example.backend_recrutement.model.entity.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<UserDTO> getpageHome(){
        return ((List<Agent>) userRepository
                .findAll())
                .stream()
                .map(this::convertDataIntoDTO)
                .collect(Collectors.toList());
    }
    // create convertDataIntoDTO() method that returns UserDTO
    private UserDTO convertDataIntoDTO (Agent userData) {
        // create instance of our UserLocationDTO class
        UserDTO dto = new UserDTO();
        dto.setIdAgent(userData.getIdAgent());
        dto.setEmail(userData.getEmail());
        dto.setPassword(userData.getPassword());
        return dto;
    }

}
