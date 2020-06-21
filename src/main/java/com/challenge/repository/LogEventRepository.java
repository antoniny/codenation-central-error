package com.challenge.repository;

import com.challenge.entity.LogEvent;
import com.challenge.entity.QLogEvent;
import com.challenge.service.dto.LevelErrorEnum;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Optional;

@Repository
public interface LogEventRepository extends BaseRepository<LogEvent, Long> , QuerydslPredicateExecutor<LogEvent>, QuerydslBinderCustomizer<QLogEvent> {


    Optional<LogEvent> findByLevelErrorEnumAndEventDescriptionIgnoreCaseAndStatus(LevelErrorEnum levelErrorEnum, String eventDescription, String status);

    Optional<Page<LogEvent>> findAllByStatusIgnoreCase(String status, Pageable pageable);

    Optional<LogEvent> findByIdAndStatusIgnoreCase(Long id, String status);

    Page<LogEvent> findAll(Predicate predicate, Pageable pageable);


    @SuppressWarnings("NullableProblems")
    @Override
    default void customize(QuerydslBindings bindings, QLogEvent logevent) {

        bindings.excluding(logevent.id);

        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

        bindings.bind(logevent.eventDate).all((path, value) -> {
            Iterator<? extends LocalDateTime> it = value.iterator();
            LocalDateTime from = it.next();
            if (value.size() >= 2) {
                LocalDateTime to = it.next();
                return Optional.of(path.between(from, to));
            } else {
                return Optional.of(path.goe(from));
            }
        });

        bindings.bind(logevent.eventCount).first(SimpleExpression::eq);
    }


}
