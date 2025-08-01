package ru.practicum.server;

import ru.practicum.EndpointHitDto;
import ru.practicum.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {
    EndpointHitDto saveHit(EndpointHitDto hit);

    List<ViewStats> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);
}