package com.microservice.product.service;

import com.microservice.product.data.dto.ProductDTO;
import com.microservice.product.data.mapper.ProductMapper;
import com.microservice.product.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepository productRepository;

    private ProductMapper productMapper;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> productMapper.toDTO(product))
                .collect(Collectors.toList());
    }

    public ProductDTO getProduct(Long id){
        return productRepository.findById(id)
                .map(product -> productMapper.toDTO(product))
                .orElse(null);
    }

    public ProductDTO save(ProductDTO productDTO){
        return productMapper.toDTO(productRepository
                .save(productMapper.fromDTO(productDTO)));
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }
}
