package com.devsuperior.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.aula.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
