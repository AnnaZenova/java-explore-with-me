package ru.practicum.ewm.compilations.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.compilations.dto.CompilationDto;
import ru.practicum.ewm.compilations.service.CompilationService;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/compilations")
@RequiredArgsConstructor
public class CompilationControllerPublic {
    private final CompilationService compilationService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<CompilationDto> getCompilations(@RequestParam(required = false) Boolean pinned,
                                                @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                                @RequestParam(defaultValue = "10") @Positive Integer size) {
        log.info("Public: Получение списка подборок событий с параметрами: pinned={}, from={}, size={}",
                pinned, from, size);
        List<CompilationDto> result = compilationService.getCompilations(pinned, from, size);
        log.info("Public: Возвращено {} подборок событий", result.size());
        return result;
    }

    @GetMapping("/{compilationId}")
    @ResponseStatus(value = HttpStatus.OK)
    public CompilationDto getCompilationById(@PathVariable Long compilationId) {
        log.info("Public: Получение подборки событий по ID: {}", compilationId);
        CompilationDto result = compilationService.getCompilationById(compilationId);
        log.info("Public: Возвращена подборка с ID: {}", compilationId);
        return result;
    }
}