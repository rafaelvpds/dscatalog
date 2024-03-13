package com.devsuperior.aula.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<ProductDTO> findAll(Pageable page) {

        Page<Product> products = productRepository
                .findAll(PageRequest.of(page.getPageNumber(), page.getPageSize(), page.getSort()));

        return products.map(x -> new ProductDTO(x));
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

        product = productRepository.save(product);

        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        Product product = productRepository.getReferenceById(id);
        convertProductTODTO(product, dto);
        product = productRepository.save(product);

        return new ProductDTO(product);
    }

    private void convertProductTODTO(Product product, ProductDTO dto) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());
        product.setDate(Instant.now());
        product.getCategories().clear();
        for (CategoryDTO cat : dto.getCategories()) {
            Category category = categoryRepository.getReferenceById(cat.getId());
            product.getCategories().add(category);
        }
    }

}
