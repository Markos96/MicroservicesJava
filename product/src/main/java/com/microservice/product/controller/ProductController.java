package com.microservice.product.controller;

import com.microservice.product.data.dto.ProductDTO;
import com.microservice.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Value("${application.name}")
    private String value;
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.save(productDTO));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        System.out.println("Es la configuracion: " + value);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
