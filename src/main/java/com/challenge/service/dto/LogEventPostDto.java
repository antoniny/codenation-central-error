package com.challenge.service.dto;

import com.challenge.entity.LogEvent;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LogEventPostDto {

    @NotNull
    private LevelErrorEnum level;
    @NotBlank(message = "Campo descrição deve ser preenchido.")
    @NotEmpty(message = "Campo descrição deve ser preenchido.")
    private String eventDescription;
    @NotBlank(message = "Campo log evento deve ser preenchido.")
    @NotEmpty(message = "Campo log evento deve ser preenchido.")
    private String eventLog;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime eventDate;


    public LogEventPostDto(LogEventPostDto postDto) {

        this.level = postDto.getLevel();
        this.eventDescription = postDto.getEventDescription();
        this.eventLog = postDto.getEventLog();
        this.eventDate = postDto.getEventDate();
    }


}
