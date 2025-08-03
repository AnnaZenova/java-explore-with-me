package ru.practicum.ewm.categories.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.categories.Category;
import ru.practicum.ewm.categories.CategoryMapper;
import ru.practicum.ewm.categories.CategoryRepository;
import ru.practicum.ewm.categories.dto.CategoryDto;
import ru.practicum.ewm.categories.dto.NewCategoryDto;
import ru.practicum.ewm.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto addCategory(NewCategoryDto newCategoryDto) {
        log.info("Добавление новой категории: {}", newCategoryDto);
        CategoryDto savedCategory = CategoryMapper.toCategoryDto(
                categoryRepository.save(CategoryMapper.toCategory(newCategoryDto))
        );
        log.info("Категория добавлена: {}", savedCategory);
        return savedCategory;
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        log.info("Обновление категории ID={}, данные: {}", categoryId, categoryDto);
        Category category = getCategory(categoryId);
        category.setName(categoryDto.getName());
        CategoryDto updatedCategory = CategoryMapper.toCategoryDto(categoryRepository.save(category));
        log.info("Категория обновлена: {}", updatedCategory);
        return updatedCategory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getCategories(Integer from, Integer size) {
        log.info("Получение категорий (from={}, size={})", from, size);
        List<CategoryDto> categories = categoryRepository.findAll(PageRequest.of(from / size, size)).stream()
                .map(CategoryMapper::toCategoryDto)
                .collect(Collectors.toList());
        log.info("Найдено {} категорий", categories.size());
        return categories;
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto getCategoryById(Long categoryId) {
        log.info("Получение категории по ID={}", categoryId);
        CategoryDto category = CategoryMapper.toCategoryDto(getCategory(categoryId));
        log.info("Найдена категория: {}", category);
        return category;
    }

    @Override
    public void deleteCategory(Long categoryId) {
        log.info("Удаление категории ID={}", categoryId);
        if (!categoryRepository.existsById(categoryId)) {
            throw new NotFoundException("Category with id=" + categoryId + " was not found");
        }
        categoryRepository.deleteById(categoryId);
        log.info("Категория ID={} удалена", categoryId);
    }

    private Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new NotFoundException("Category with id=" + categoryId + " was not found"));
    }
}
