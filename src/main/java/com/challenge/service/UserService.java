package com.challenge.service;

import com.challenge.config.exception.NotFoundException;
import com.challenge.entity.User;
import com.challenge.repository.UserRepository;
import com.challenge.service.dto.UserDto;
import com.challenge.service.dto.UserPostDto;
import com.challenge.service.dto.UserPutDto;
import com.challenge.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface {

    @Value("${user.status.active}")
    private String FLAG_STATUS_ACTIVE;

    @Value("${user.status.inactive}")
    private String FLAG_STATUS_INACTIVE;

    @Value("${user.role.post.name.default}")
    private String POST_ROLE_NAME_DEFAULT;

    @Value("${user.messages.exception.user.notfound}")
    private String MESSAGE_USER_NOT_FOUND;

    @Value("${user.messages.exception.role.notfound}")
    private String MESSAGE_ROLE_NOT_FOUND;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public List<UserDto> findAllUsers(Pageable pageable) {
        return   userRepository
                    .findAllByStatus(FLAG_STATUS_ACTIVE , pageable)
                    .orElseThrow(() -> new NotFoundException(MESSAGE_USER_NOT_FOUND))
                    .stream()
                    .map(UserDto::new)
                    .collect(Collectors.toList());
    }

    @Override
    public UserDto findUser(String email) {
        return  userRepository
                    .findByEmailIgnoreCaseAndStatus(email, FLAG_STATUS_ACTIVE)
                    .map(UserDto::new)
                    .orElseThrow(() -> new NotFoundException(MESSAGE_USER_NOT_FOUND));
    }

    @Override
    public UserDto postUser(UserPostDto userPostDto) {

        User user = new User(userPostDto);
        user.setStatus(FLAG_STATUS_ACTIVE);
        user.setRoles(Collections.singletonList(userRepository.findByRoles_Name(POST_ROLE_NAME_DEFAULT)
                                                              .orElseThrow(() -> new NotFoundException(MESSAGE_ROLE_NOT_FOUND))));
        user.setCreatedAt(LocalDateTime.now());

        return new UserDto(userRepository.save(user));
    }

    @Override
    public UserDto putUser(UserPutDto userPutDto) {

        User user = userRepository
                .findByEmailIgnoreCaseAndStatus(userPutDto.getEmail(), FLAG_STATUS_ACTIVE)
                .orElseThrow(() -> new NotFoundException(MESSAGE_USER_NOT_FOUND));

        user.setPassword(userPutDto.getPassword());

        return new UserDto(userRepository.save(user));
    }

    @Override
    public UserDto deleteUser(String email) {
        User user = userRepository
                .findByEmailIgnoreCaseAndStatus(email , FLAG_STATUS_ACTIVE)
                .orElseThrow(() -> new NotFoundException(MESSAGE_USER_NOT_FOUND));

        user.setStatus(FLAG_STATUS_INACTIVE);

        return new UserDto(userRepository.save(user));
    }
}
