package com.challenge.entity;

import com.challenge.service.dto.LevelErrorEnum;
import com.challenge.service.dto.LogEventPostDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity(name = "log_event")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class LogEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LevelErrorEnum levelErrorEnum;

    @NotBlank
    @NotEmpty
    private String eventDescription;

    @NotBlank
    @NotEmpty
    private String eventLog;

    @NotBlank
    @NotEmpty
    private String origin;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime eventDate;

    @PositiveOrZero
    @NotNull
    private Long eventCount;

    @NotBlank
    @NotEmpty
    private String status;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @CreatedDate
    private LocalDateTime createdAt;

    //@ManyToOne
    //@JsonIgnore
    //private User user;


    public LogEvent(LogEventPostDto postDto) {
        this.levelErrorEnum = postDto.getLevel();
        this.eventDescription = postDto.getEventDescription();
        this.eventLog = postDto.getEventLog();
        this.eventDate = postDto.getEventDate();
        this.origin = "Origin";
        this.eventCount = 1L;
        this.status = "S";
        this.createdAt = LocalDateTime.now();
        //this.user
    }
}
