package com.usersapp.usersapp.services;

import com.usersapp.usersapp.entities.UserEntity;
import com.usersapp.usersapp.shared.UserDto;

public interface UsersService {
 UserDto createUser(UserDto userDto);
}
