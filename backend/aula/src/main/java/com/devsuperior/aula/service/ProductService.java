package com.devsuperior.aula.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.aula.dto.CategoryDTO;
import com.devsuperior.aula.dto.ProductDTO;
import com.devsuperior.aula.model.Category;
import com.devsuperior.aula.model.Product;
import com.devsuperior.aula.repository.CategoryRepository;
import com.devsuperior.aula.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductDTO findByid(Long id) {

        Product product = productRepository.findById(id).orElseThrow();

        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product product = new Product();

        convertProductTODTO(product, dto);

        return new ProductDTO(product);
    }

    private void convertProductTODTO(Product product, ProductDTO dto) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());
        product.getCategories().clear();
        for (CategoryDTO cat : dto.getCategories()) {
            Category category = categoryRepository.getReferenceById(cat.getId());
            product.getCategories().add(category);
        }
    }

}
