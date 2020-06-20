package com.challenge.resource;

import com.challenge.service.LogEventService;
import com.challenge.service.dto.LogEventListDto;
import com.challenge.service.dto.LogEventPostDto;
import com.challenge.service.dto.LogEventPostResponseDto;
import com.challenge.service.dto.LogEventResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("api/v1")
public class LogEventController {


    final LogEventService logEventService;

    public LogEventController(LogEventService logEventService) {
        this.logEventService = logEventService;
    }


    @PostMapping(value = "/logevents",
                 consumes = MediaType.APPLICATION_JSON_VALUE ,
                 produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LogEventPostResponseDto> postLogEvent(@Valid @RequestBody LogEventPostDto postDto){
        return new ResponseEntity( logEventService.postLogEvent(postDto) , HttpStatus.CREATED);
    }


    @GetMapping(value = "/logevents",
                produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Page<LogEventListDto>> getAllLogEvent(Pageable pageable){
        return new ResponseEntity( logEventService.getAllLogEvent(pageable) , HttpStatus.OK);
    }

    @GetMapping(value = "/logevents/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LogEventResponseDto> getLogEvent(@PathVariable("id") Long id){
        return new ResponseEntity( logEventService.getLogEvent(id) , HttpStatus.OK);
    }


}
