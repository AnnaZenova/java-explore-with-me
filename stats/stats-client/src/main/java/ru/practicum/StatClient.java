package ru.practicum;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class StatClient extends BaseClient {
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    @Value("${client.url}")
    private String serverUrl;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    public ResponseEntity<Object> saveHit(EndpointHitDto hit) {
        return post(serverUrl + "/hit", hit);
    }

    public ResponseEntity<Object> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        StringBuilder url = new StringBuilder(serverUrl + "/stats?");
        url.append("start=").append(start.format(formatter));
        url.append("&end=").append(end.format(formatter));

        if (uris != null && !uris.isEmpty()) {
            for (String uri : uris) {
                url.append("&uris=").append(uri);
            }
        }
        url.append("&unique=").append(unique);

        return get(url.toString());
    }
}