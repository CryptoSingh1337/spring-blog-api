package com.saransh.springblog.service.impl;

import com.saransh.springblog.domain.Category;
import com.saransh.springblog.exception.ResourceNotFoundException;
import com.saransh.springblog.repository.CategoryRepository;
import com.saransh.springblog.service.CategoryService;
import com.saransh.springblog.web.mapper.CategoryMapper;
import com.saransh.springblog.web.model.CategoryDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by CryptSingh1337 on 8/2/2021
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> findAll() {
        log.debug("Retrieving all the category");
        return StreamSupport.
                stream(categoryRepository.findAll().spliterator(), false)
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(int categoryId) {
        log.debug("Retrieving the category with Id: {}", categoryId);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category with Id: " + categoryId + " not found"));
        return categoryMapper.categoryToCategoryDTO(category);
    }
}
