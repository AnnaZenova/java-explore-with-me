package ru.practicum.ewm.categories.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import ru.practicum.ewm.categories.dto.CategoryDto;
import ru.practicum.ewm.categories.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryControllerPublic {
    private final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> getCategories(
            @RequestParam(value = "from", defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(value = "size", defaultValue = "10") @Positive Integer size) {
        log.info("Запрос списка категорий: from={}, size={}", from, size);
        List<CategoryDto> categories = categoryService.getCategories(from, size);
        log.info("Найдено {} категорий", categories.size());
        return categories;
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getCategoryById(@PathVariable Long categoryId) {
        log.info("Запрос категории по ID={}", categoryId);
        CategoryDto category = categoryService.getCategoryById(categoryId);
        log.info("Найдена категория: {}", category);
        return category;
    }
}
