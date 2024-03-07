package com.devsuperior.aula.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.aula.dto.CategoryDTO;
import com.devsuperior.aula.model.Category;
import com.devsuperior.aula.repository.CategoryRepository;
import com.devsuperior.aula.service.exceptions.DatabaseException;
import com.devsuperior.aula.service.exceptions.ResourceNotFoundExceptions;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Categoria não encontrada"));

        return new CategoryDTO(category);
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> category = categoryRepository.findAll();

        return category.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category = categoryRepository.save(category);

        return new CategoryDTO(category);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        Category entity = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Categoria não encontrada"));
        entity.setName(dto.getName());
        entity.setUpdatedAt(entity.getUpdatedAt());
        entity = categoryRepository.save(entity);

        return new CategoryDTO(entity);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundExceptions("Id not found " + id);
        }
        try {
            categoryRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

}
