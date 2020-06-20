package com.challenge.repository;

import com.challenge.entity.LogEvent;
import com.challenge.service.dto.LevelErrorEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogEventRepository extends CrudRepository<LogEvent, Long> {


    Optional<LogEvent> findByLevelErrorEnumAndEventDescriptionIgnoreCaseAndStatus(LevelErrorEnum levelErrorEnum, String eventDescription, String status);


    Optional<Page<LogEvent>> findAllByStatusIgnoreCase(String status, Pageable pageable);

    Optional<LogEvent> findByIdAndStatusIgnoreCase(Long id, String status);


}
