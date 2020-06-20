package com.challenge.service.interfaces;

import com.challenge.service.dto.LogEventListDto;
import com.challenge.service.dto.LogEventPostDto;
import com.challenge.service.dto.LogEventPostResponseDto;
import com.challenge.service.dto.LogEventResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LogEventInterface {


    LogEventPostResponseDto postLogEvent(LogEventPostDto postDto);

    Page<LogEventListDto> getAllLogEvent(Pageable pageable);

    LogEventResponseDto getLogEvent(Long id);
}
