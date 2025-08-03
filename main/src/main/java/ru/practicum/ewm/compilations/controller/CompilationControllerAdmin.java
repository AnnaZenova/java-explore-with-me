package ru.practicum.ewm.compilations.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.compilations.dto.CompilationDto;
import ru.practicum.ewm.compilations.dto.NewCompilationDto;
import ru.practicum.ewm.compilations.dto.UpdateCompilationRequest;
import ru.practicum.ewm.compilations.service.CompilationService;

import jakarta.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/admin/compilations")
@RequiredArgsConstructor
public class CompilationControllerAdmin {
    private final CompilationService compilationService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompilationDto addCompilation(@RequestBody @Valid NewCompilationDto newCompilationDto) {
        log.info("Admin: Добавление новой подборки событий с заголовком: {}", newCompilationDto.getTitle());
        CompilationDto result = compilationService.addCompilation(newCompilationDto);
        log.info("Admin: Успешно добавлена подборка с ID: {}", result.getId());
        return result;
    }

    @PatchMapping("/{compilationId}")
    @ResponseStatus(value = HttpStatus.OK)
    public CompilationDto updateCompilation(@PathVariable Long compilationId,
                                            @RequestBody @Valid UpdateCompilationRequest updateCompilation) {
        log.info("Admin: Обновление подборки событий с ID: {}", compilationId);
        CompilationDto result = compilationService.updateCompilation(compilationId, updateCompilation);
        log.info("Admin: Успешно обновлена подборка с ID: {}", compilationId);
        return result;
    }

    @DeleteMapping("/{compilationId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCompilation(@PathVariable long compilationId) {
        log.info("Admin: Удаление подборки событий с ID: {}", compilationId);
        compilationService.deleteCompilation(compilationId);
        log.info("Admin: Успешно удалена подборка с ID: {}", compilationId);
    }
}