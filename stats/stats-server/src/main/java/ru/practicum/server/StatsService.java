package ru.practicum.server;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.EndpointHit;
import ru.practicum.EndpointHitDto;
import ru.practicum.ViewStats;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StatsService {
    private final StatsRepository statRepository;

    public EndpointHitDto saveHit(EndpointHitDto hit) {
        EndpointHit endpointHit = statRepository.save(EndpointHitMapper.toEndpointHit(hit));
        return EndpointHitMapper.toEndpointHitDto(endpointHit);
    }

    @Transactional(readOnly = true)
    public List<ViewStats> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        if (start.isAfter(end)) {
            throw new DateTimeException("End date must be after start date.");
        }

        boolean hasUris = uris != null && !uris.isEmpty();

        if (unique) {
            return hasUris
                    ? statRepository.findHitsWithUniqueIpWithUris(uris, start, end)
                    : statRepository.findHitsWithUniqueIpWithoutUris(start, end);
        } else {
            return hasUris
                    ? statRepository.findAllHitsWithUrls(uris, start, end)
                    : statRepository.findAllHitsWithoutUrls(start, end);
        }
    }
}
