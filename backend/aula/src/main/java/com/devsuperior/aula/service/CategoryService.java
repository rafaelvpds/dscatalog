package com.devsuperior.aula.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.aula.dto.CategoryDTO;
import com.devsuperior.aula.model.Category;
import com.devsuperior.aula.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow();

        return new CategoryDTO(category);
    }

    public List<CategoryDTO> findAll() {
        List<Category> category = categoryRepository.findAll();

        return category.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }

    public CategoryDTO inser(CategoryDTO dto) {
        Category category = new Category();

        category = categoryRepository.save(category);
        convertToDTO(category, dto);

        return new CategoryDTO(category);
    }

    private void convertToDTO(Category category, CategoryDTO dto) {
        category.setName(dto.getName());
    }
}
