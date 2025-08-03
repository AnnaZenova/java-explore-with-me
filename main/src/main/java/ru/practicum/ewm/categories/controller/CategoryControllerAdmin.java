package ru.practicum.ewm.categories.controller;

import ru.practicum.ewm.categories.dto.CategoryDto;
import ru.practicum.ewm.categories.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.categories.dto.NewCategoryDto;

import jakarta.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class CategoryControllerAdmin {
    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CategoryDto addCategory(@RequestBody @Valid NewCategoryDto newCategoryDto) {
        log.info("Добавление новой категории: {}", newCategoryDto);
        CategoryDto createdCategory = categoryService.addCategory(newCategoryDto);
        log.info("Категория успешно добавлена: {}", createdCategory);
        return createdCategory;
    }

    @PatchMapping("/{catId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto updateCategory(@PathVariable Long catId, @RequestBody @Valid CategoryDto categoryDto) {
        log.info("Обновление категории с ID={}, новые данные: {}", catId, categoryDto);
        CategoryDto updatedCategory = categoryService.updateCategory(catId, categoryDto);
        log.info("Категория успешно обновлена: {}", updatedCategory);
        return updatedCategory;
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long catId) {
        log.info("Удаление категории с ID={}", catId);
        categoryService.deleteCategory(catId);
        log.info("Категория с ID={} успешно удалена", catId);
    }
}