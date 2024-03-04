package com.devsuperior.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.aula.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
