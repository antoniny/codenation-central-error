package com.challenge.resource;

import com.challenge.config.exception.dto.ExceptionHandlerResponseDTO;
import com.challenge.entity.LogEvent;
import com.challenge.repository.LogEventRepository;
import com.challenge.service.LogEventService;
import com.challenge.service.dto.*;
import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@RestController
@RequestMapping("api/v1")
@Tag(name = "Log Event" , description = "Registra log - evento de erros.")
public class LogEventController {


    private final LogEventService logEventService;

    public LogEventController(LogEventService logEventService) {
        this.logEventService = logEventService;
    }

    @Autowired
    private LogEventRepository logEventRepository;





    @Operation(summary = "Busca eventos log por id", description = "Busca evento log por id." , tags = {"Log Event"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento de erro localizado com sucesso.",  content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "404", description = "Evento de erro não localizado.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionHandlerResponseDTO.class)))) })

    @GetMapping(value = "/logevents/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<LogEventResponseDto> getLogEvent(@PathVariable("id") Long id){
        return new ResponseEntity( logEventService.getLogEvent(id) , HttpStatus.OK);
    }

    @Operation(summary = "Busca eventos log", description = "Busca todos os eventos log ativos." , tags = {"Log Event"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eventos de erro localizados com sucesso.",  content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "404", description = "Eventos de erro não localizados.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionHandlerResponseDTO.class)))) })

    @GetMapping(value = "/logevents", produces = MediaType.APPLICATION_JSON_VALUE  )
    public ResponseEntity<Page<LogEventListDto>> getAllLogEvent(
         @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) @ParameterObject Pageable pageable
    ){
        return new ResponseEntity( logEventService.getAllLogEvent(pageable) , HttpStatus.OK);
    }

    @Operation(summary = "Grava evento log", description = "Grava evento log de erro." , tags = {"Log Event"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Evento log gravado com sucesso.",  content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "400", description = "Requisição com falha.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionHandlerResponseDTO.class)))) })

    @PostMapping(value = "/logevents", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<LogEventPostResponseDto> postLogEvent(@Valid @RequestBody LogEventPostDto postDto){
        return new ResponseEntity( logEventService.postLogEvent(postDto) , HttpStatus.CREATED);
    }


    @Operation(summary = "Busca eventos log", description = "Busca todos os eventos log ativos." , tags = {"Log Event"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eventos de erro localizados com sucesso.",  content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "404", description = "Eventos de erro não localizados.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExceptionHandlerResponseDTO.class)))) })

    @GetMapping(value = "/logs", produces = MediaType.APPLICATION_JSON_VALUE  )
    public ResponseEntity<Page<LogEventListDto>> findAllLogEvent(
            @Parameter(hidden = true) @QuerydslPredicate(root = LogEvent.class) Predicate predicate,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) @ParameterObject Pageable pageable) {

        return new ResponseEntity( logEventService.findAllLogEvent(predicate , pageable) , HttpStatus.OK);
    }



}
