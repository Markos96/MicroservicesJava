package com.microservice.product.controller;

import com.microservice.product.data.dto.ProductDTO;
import com.microservice.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) throws InterruptedException {
        if(id == 10){
            throw new IllegalStateException("Product not found");
        }
        if(id.equals(7L)){
            TimeUnit.SECONDS.sleep(5L);
        }
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
