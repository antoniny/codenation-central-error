package com.challenge.service.dto;

import com.challenge.entity.LogEvent;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LogEventPostResponseDto {


    private Long id;
    private LevelErrorEnum levelErrorEnum;
    private String eventDescription;
    private String eventLog;
    private String origin;
    private LocalDateTime eventDate;
    private Long eventCount;
    private String status;
    @JsonDeserialize
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;
    private String clientId;


    public LogEventPostResponseDto(LogEvent logEvent) {
        this.id = logEvent.getId();
        this.levelErrorEnum = logEvent.getLevelErrorEnum();
        this.eventDescription = logEvent.getEventDescription();
        this.eventLog = logEvent.getEventLog();
        this.origin = logEvent.getOrigin();
        this.eventDate = logEvent.getEventDate();
        this.eventCount = logEvent.getEventCount();
        this.status = logEvent.getStatus();
        this.createdAt = logEvent.getCreatedAt();
        //this.clientId = logEvent.getUser().getEmail();
    }
}
