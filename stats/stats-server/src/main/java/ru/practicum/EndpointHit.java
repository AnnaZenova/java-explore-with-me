package ru.practicum;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "hits")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EndpointHit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "App name cannot be blank")
    private String app;

    @Column(name = "uri",nullable = false)
    @NotBlank(message = "URI cannot be blank")
    private String uri;

    @Column(nullable = false, length = 15)
    @NotBlank(message = "IP cannot be blank")
    private String ip;

    @Column(nullable = false)
    @JsonFormat(pattern = EndpointHitDto.DATE_TIME_PATTERN)
    @NotNull(message = "Timestamp cannot be null")
    private LocalDateTime timestamp;

    public EndpointHit(String app, String uri, String ip, LocalDateTime timestamp) {
        this.app = app;
        this.uri = uri;
        this.ip = ip;
        this.timestamp = timestamp;
    }
}
