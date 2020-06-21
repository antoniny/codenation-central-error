package com.challenge.service;

import com.challenge.config.exception.NotFoundException;
import com.challenge.config.exception.SavePostException;
import com.challenge.entity.LogEvent;
import com.challenge.repository.LogEventRepository;
import com.challenge.service.dto.LogEventListDto;
import com.challenge.service.dto.LogEventPostDto;
import com.challenge.service.dto.LogEventPostResponseDto;
import com.challenge.service.dto.LogEventResponseDto;
import com.challenge.service.interfaces.LogEventInterface;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Service
public class LogEventService implements LogEventInterface {

    @Value("${logevent.messages.exception.post}")
    private String EXCEPTION_MESSAGE_POST;

    @Value("${logevent.messages.exception.notfound}")
    private String EXCEPTION_MESSAGE_FOUND;

    @Value("${logevent.status.active}")
    private String FLAG_STATUS_ACTIVE;


    final LogEventRepository logEventRepository;

    public LogEventService(LogEventRepository logEventRepository) {
        this.logEventRepository = logEventRepository;
    }

    @Override
    public LogEventPostResponseDto postLogEvent(LogEventPostDto postDto) {

        LogEvent logEvent =  logEventRepository
                                    .findByLevelErrorEnumAndEventDescriptionIgnoreCaseAndStatus(postDto.getLevel() , postDto.getEventDescription() , FLAG_STATUS_ACTIVE)
                                    .map(event -> { event.setEventCount(event.getEventCount() + 1L);
                                                    event.setCreatedAt(LocalDateTime.now());
                                                    return  event;  })
                                    .orElseGet(() -> new LogEvent(postDto));

        return   Stream.of(logEventRepository.save(logEvent))
                      .map(LogEventPostResponseDto::new)
                      .findAny()
                      .orElseThrow(() -> new SavePostException(EXCEPTION_MESSAGE_POST));


    }

    @Override
    public Page<LogEventListDto> getAllLogEvent(Pageable pageable) {

        Page<LogEvent> logEventPage = logEventRepository.findAllByStatusIgnoreCase(FLAG_STATUS_ACTIVE,pageable)
                                                        .orElseThrow(() -> new NotFoundException(EXCEPTION_MESSAGE_FOUND));

        List<LogEventListDto> listDtos = logEventPage.map(LogEventListDto::new).toList();

        return new PageImpl<>(listDtos, pageable, logEventPage.getTotalElements());
    }

    @Override
    public LogEventResponseDto getLogEvent(Long id) {
        return logEventRepository.findByIdAndStatusIgnoreCase(id , FLAG_STATUS_ACTIVE)
                                 .map(LogEventResponseDto::new)
                                 .orElseThrow(() -> new NotFoundException(EXCEPTION_MESSAGE_FOUND));
    }

    @Override
    public Page<LogEventListDto> findAllLogEvent(Predicate predicate, Pageable pageable) {

        Page<LogEvent> logEventPage = logEventRepository.findAll(predicate ,pageable);

        List<LogEventListDto> listDtos = logEventPage.map(LogEventListDto::new).toList();

        return new PageImpl<>(listDtos, pageable, logEventPage.getTotalElements());
    }


}
