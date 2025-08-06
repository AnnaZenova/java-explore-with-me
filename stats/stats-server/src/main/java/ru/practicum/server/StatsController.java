package ru.practicum.server;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import ru.practicum.EndpointHitDto;
import ru.practicum.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class StatsController {
    private final StatsServiceImpl statService;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public EndpointHitDto saveHit(@RequestBody @Valid EndpointHitDto hit) {
        log.info("Received request to save hit: {}", hit);
        EndpointHitDto savedHit = statService.saveHit(hit);
        log.info("Hit successfully saved: {}", savedHit);
        return savedHit;
    }

    @GetMapping("/stats")
    public List<ViewStats> getStats(
            @RequestParam @DateTimeFormat(pattern = EndpointHitDto.DATE_TIME_PATTERN) LocalDateTime start,
            @RequestParam @DateTimeFormat(pattern = EndpointHitDto.DATE_TIME_PATTERN) LocalDateTime end,
            @RequestParam(required = false) List<String> uris,
            @RequestParam(defaultValue = "false") Boolean unique) {

        log.info("Received statistics request with parameters: start={}, end={}, uris={}, unique={}",
                start, end, uris, unique);

        List<ViewStats> stats = statService.getStats(start, end, uris, unique);

        log.info("Statistics returned: {} records", stats.size());
        return stats;
    }
}