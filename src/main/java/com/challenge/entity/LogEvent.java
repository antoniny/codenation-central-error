package com.challenge.entity;

import com.challenge.service.dto.LevelErrorEnum;
import com.challenge.service.dto.LogEventPostDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Entity(name = "log_event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime eventDate;

    @PositiveOrZero
    @NotNull
    private Long eventCount;

    @NotBlank
    @NotEmpty
    private String status;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;


    public LogEvent(LogEventPostDto postDto) {
        this.levelErrorEnum = postDto.getLevel();
        this.eventDescription = postDto.getEventDescription();
        this.eventLog = postDto.getEventLog();
        this.eventDate = postDto.getEventDate();
        this.origin = "Origin";
        this.eventCount = 1L;
        this.status = "S";
    }
}
