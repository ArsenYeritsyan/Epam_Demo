package com.epam.demo.managerassignment.service;

import com.epam.demo.managerassignment.model.User;
import com.epam.demo.managerassignment.model.restmodel.UserResponseModel;
import com.epam.demo.managerassignment.repo.UserRepository;
import com.epam.demo.managerassignment.security.ApplicationUserRole;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleUserService {
    private UserRepository userRepo;

    public RoleUserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public ResponseEntity mustBeManager(Long id) {
        Optional<User> managerOptional = userRepo.findById(id);
        return mustBeManager(managerOptional);
    }

    public ResponseEntity mustBeManager(Optional<User> user) {

        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (!user.get().getRole().equals(ApplicationUserRole.USER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return null;
    }

    public ResponseEntity mustBeWaiter(Long id){
        Optional waiterOptional = userRepo.findById(id);
        return mustBeWaiter(waiterOptional);
    }

    public ResponseEntity mustBeWaiter(Optional<UserResponseModel> user){
        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (user.get().isManager()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return null;
    }
}
