package com.usersapp.usersapp.controllers;

import com.usersapp.usersapp.models.CreateUserRequestModel;
import com.usersapp.usersapp.models.CreateUserResponseModel;
import com.usersapp.usersapp.services.UserServiceImpl;
import com.usersapp.usersapp.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/users")
public class UserController {

    private final Environment environment;
    private final UserServiceImpl userService;

    @Autowired
    public UserController(Environment environment, UserServiceImpl userService) {
        this.environment = environment;
        this.userService = userService;
    }

    @GetMapping("/status/check")
    public String status() {
        return "Current port " + environment.getProperty("local.server.port");
    }

    @PostMapping("/signup")
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel createUserRequestModel) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(createUserRequestModel, UserDto.class);
        UserDto user = userService.createUser(userDto);
        CreateUserResponseModel responseModel = modelMapper.map(user, CreateUserResponseModel.class);
        return new ResponseEntity<>(responseModel,HttpStatus.CREATED);
    }
}
