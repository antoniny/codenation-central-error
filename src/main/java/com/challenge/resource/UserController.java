package com.challenge.resource;

import com.challenge.config.exception.NotFoundException;
import com.challenge.config.exception.dto.ExceptionHandlerResponseDTO;
import com.challenge.service.UserService;
import com.challenge.service.dto.UserDto;
import com.challenge.service.dto.UserPostDto;
import com.challenge.service.dto.UserPutDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Usuários" , description = "Cadastro de usuário")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @Operation(summary = "Busca usuários", description = "Busca todos os usuários ativos ou usuário por login(e-mail)" , tags = {"Usuários"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o(s) usuário(s)",  content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "404", description = "Retorna um erro 404 caso nenhum usuário seja encontrado", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionHandlerResponseDTO.class)))) })
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<UserDto>> getUsers(
            @RequestParam(value = "email", required = false) Optional<String> email ,
            @RequestParam(required = false) Pageable pageable
    ) {

        return new ResponseEntity( email.isPresent()
                                            ? userService.findUser(email.get())
                                            : userService.findAllUsers(pageable)
                                        , HttpStatus.OK
                                );
    }


    @Operation(summary = "Grava usuário", description = "Grava usuário" , tags = {"Usuários"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário gravado com sucesso",  content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "404", description = "Retorna um erro 404 caso nenhum perfil de usuário seja encontrado", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionHandlerResponseDTO.class)))) })
    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDto> postUser(
            @Valid @RequestBody UserPostDto userPostDto
    ) {

        return new ResponseEntity(userService.postUser(userPostDto), HttpStatus.OK);
    }


    @Operation(summary = "Altera usuário", description = "Altera usuário (password)" , tags = {"Usuários"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário alterado com sucesso",  content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "404", description = "Retorna um erro 404 caso nenhum usuário seja encontrado", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionHandlerResponseDTO.class)))) })
    @PutMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE
        )
    public ResponseEntity<UserDto> putUser(
            @Valid @RequestBody UserPutDto userPutDto
    ) {
        return new ResponseEntity(userService.putUser(userPutDto), HttpStatus.CREATED);
    }


    @Operation(summary = "Excluir usuário", description = "Desativa usuário" , tags = {"Usuários"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário desativado com sucesso",  content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "404", description = "Retorna um erro 404 caso nenhum usuário seja encontrado", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionHandlerResponseDTO.class)))) })
    @DeleteMapping(value = "/users",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDto> deleteUser(
          @Valid @Email @RequestParam(value = "email") String email
    ) {

        return new ResponseEntity(userService.deleteUser(email), HttpStatus.OK);
    }



}
