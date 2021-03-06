package com.epam.demo.managerassignment.service;

import com.epam.demo.managerassignment.exceptions.DataNotFoundException;
import com.epam.demo.managerassignment.model.User;
import com.epam.demo.managerassignment.model.restmodel.UserRequestModel;
import com.epam.demo.managerassignment.model.restmodel.UserResponseModel;
import com.epam.demo.managerassignment.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User requestToUser(UserRequestModel userRequestModel) {
        User user = new User();
        user.setUsername(userRequestModel.getUsername());
        user.setPassword(userRequestModel.getPassword());
        return user;
    }

    private UserResponseModel userToResponse(User user) {
        UserResponseModel userResponseModel = new UserResponseModel();
        userResponseModel.setId(user.getId());
        userResponseModel.setUsername(user.getUsername());
        userResponseModel.setPassword(user.getPassword());
        return userResponseModel;
    }

    public UserResponseModel createUser(UserRequestModel userRequestModel) {
        User user = requestToUser(userRequestModel);
        userRepository.save(user);
        return userToResponse(user);
    }

    public List<UserResponseModel> findAll() {
        List<User> all = userRepository.findAll();
        return all.stream()
                .map(this::userToResponse)
                .collect(Collectors.toList());
    }

    public UserResponseModel findById(Long id) {
        User user;
        if (userRepository.findById(id).isPresent()) {
            user = (User) userRepository.findById(id).get();
        } else {
            throw new DataNotFoundException("The user not found");
        }
        return userToResponse(user);
    }

    public UserResponseModel updateUser(Long id, UserRequestModel userRequestModel) {
        User user;
        if (userRepository.findById(id).isPresent()) {
            user = (User) userRepository.findById(id).get();
        } else {
            throw new DataNotFoundException("The user not found");
        }
        user.setUsername(userRequestModel.getUsername());
        user.setPassword(userRequestModel.getPassword());
        userRepository.save(user);
        return userToResponse(user);
    }

    public UserResponseModel deleteUser(Long id) {
        userRepository.deleteById(id);
        return null;
    }

}
