package com.microservice.product.data.mapper;

import com.microservice.product.data.dto.ProductDTO;
import com.example.common.model.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private ModelMapper modelMapper;

    public ProductDTO toDTO (Product product){
        return modelMapper.map(product, ProductDTO.class);
    }

    public Product fromDTO (ProductDTO productDTO){
        return modelMapper.map(productDTO, Product.class);
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
