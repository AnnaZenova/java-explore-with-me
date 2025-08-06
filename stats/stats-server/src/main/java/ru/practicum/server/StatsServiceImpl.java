package ru.practicum.server;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.EndpointHit;
import ru.practicum.EndpointHitDto;
import ru.practicum.ViewStats;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final StatsRepository statRepository;

    @Override
    public EndpointHitDto saveHit(EndpointHitDto hit) {
        log.info("Saving hit: {}", hit);
        EndpointHit endpointHit = statRepository.save(EndpointHitMapper.toEndpointHit(hit));
        EndpointHitDto savedHit = EndpointHitMapper.toEndpointHitDto(endpointHit);
        log.info("Hit successfully saved");
        return savedHit;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ViewStats> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        log.info("Getting statistics with parameters: start={}, end={}, uris={}, unique={}", start, end, uris, unique);
        if (start.isAfter(end)) {
            log.error("Date validation error: start {} is after end {}", start, end);
            throw new DateTimeException("End date must be after start date.");
        }

        boolean hasUris = uris != null && !uris.isEmpty();
        List<ViewStats> result;

        if (unique) {
            result = hasUris
                    ? statRepository.findHitsWithUniqueIpWithUris(uris, start, end)
                    : statRepository.findHitsWithUniqueIpWithoutUris(start, end);
        } else {
            result = hasUris
                    ? statRepository.findAllHitsWithUrls(uris, start, end)
                    : statRepository.findAllHitsWithoutUrls(start, end);
        }

        log.info("Returned {} statistics records", result.size());
        return result;
    }
}