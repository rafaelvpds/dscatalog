package com.devsuperior.aula.dto;

import java.time.Instant;

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
    private Instant createdAt;
    private Instant updateAt;

    public CategoryDTO(Category entity) {
        id = entity.getId();
        name = entity.getName();
        createdAt = entity.getCreatedAt();
        updateAt = entity.getUpdatedAt();
    }
}
