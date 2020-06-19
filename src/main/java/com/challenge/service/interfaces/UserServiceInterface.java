package com.challenge.service.interfaces;

import com.challenge.service.dto.UserDto;
import com.challenge.service.dto.UserPostDto;
import com.challenge.service.dto.UserPutDto;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface UserServiceInterface {

    List<UserDto> findAllUsers(Pageable pageable);

    UserDto findUser(String email);

    UserDto postUser(UserPostDto userPostDto);

    UserDto putUser(UserPutDto userPutDto);

    UserDto deleteUser(String email);

}
