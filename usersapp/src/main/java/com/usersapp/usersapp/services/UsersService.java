package com.usersapp.usersapp.services;

import com.usersapp.usersapp.entities.UserEntity;
import com.usersapp.usersapp.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {
 UserDto createUser(UserDto userDto);
 UserDto getUsersDetailsByEmail(String email);
}
