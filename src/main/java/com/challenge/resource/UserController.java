package com.challenge.resource;

import com.challenge.service.UserService;
import com.challenge.service.dto.UserDto;
import com.challenge.service.dto.UserPostDto;
import com.challenge.service.dto.UserPutDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/users",
            produces = MediaType.APPLICATION_JSON_VALUE    )
    public ResponseEntity<List<UserDto>> getUsers(
            @RequestParam(value = "email", required = false) Optional<String> email ,
            Pageable pageable
    ) {

        return new ResponseEntity( email.isPresent()
                                            ? userService.findUser(email.get())
                                            : userService.findAllUsers(pageable)
                                        , HttpStatus.OK
                                );
    }


    @PostMapping(value = "/users",
            consumes = MediaType.APPLICATION_JSON_VALUE ,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDto> postUser(
            @Valid @RequestBody UserPostDto userPostDto
    ) {

        return new ResponseEntity(userService.postUser(userPostDto), HttpStatus.OK);
    }

    @PutMapping(value = "/users",
            consumes = MediaType.APPLICATION_JSON_VALUE ,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDto> putUser(
            @Valid @RequestBody UserPutDto userPutDto
    ) {

        return new ResponseEntity(userService.putUser(userPutDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/users",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDto> deleteUser(
          @Valid @Email @RequestParam(value = "email", required = false) String email
    ) {

        return new ResponseEntity(userService.deleteUser(email), HttpStatus.OK);
    }



}
