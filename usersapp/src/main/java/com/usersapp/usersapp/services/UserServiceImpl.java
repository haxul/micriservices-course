package com.usersapp.usersapp.services;

import com.usersapp.usersapp.shared.UserDto;
import com.usersapp.usersapp.entities.UserEntity;
import com.usersapp.usersapp.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl  implements UsersService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity user = modelMapper.map(userDto, UserEntity.class);
        user.setEncryptedPassword("test");
        userRepository.save(user);
        UserDto returnedValue = modelMapper.map(user, UserDto.class);
        return returnedValue;
    }
}
