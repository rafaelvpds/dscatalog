package com.devsuperior.aula.dto;

import java.util.List;

import com.devsuperior.aula.model.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private List<ProductDTO> products;

    public CategoryDTO(Category entity) {
        id = entity.getId();
        name = entity.getName();

    }
}
