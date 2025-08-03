package ru.practicum.ewm.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.EndpointHitDto;
import ru.practicum.ewm.categories.dto.CategoryDto;
import ru.practicum.ewm.events.model.State;
import ru.practicum.ewm.locations.LocationDto;
import ru.practicum.ewm.users.dto.UserShortDto;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventFullDtoWithViews {
    Long id;

    String annotation;

    CategoryDto category;

    Long confirmedRequests;

    @JsonFormat(pattern = EndpointHitDto.DATE_TIME_PATTERN)
    LocalDateTime createdOn;

    String description;

    @JsonFormat(pattern = EndpointHitDto.DATE_TIME_PATTERN)
    LocalDateTime eventDate;

    UserShortDto initiator;

    LocationDto location;

    boolean paid;

    Integer participantLimit;

    @JsonFormat(pattern = EndpointHitDto.DATE_TIME_PATTERN)
    LocalDateTime publishedOn;

    boolean requestModeration;

    State state;

    String title;

    Long views;
}