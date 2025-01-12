package com.microservice.item.controller;

import com.example.common.model.entity.Product;
import com.microservice.item.data.model.Item;
import com.microservice.item.service.ItemService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Value("${application.name}")
    private String value;
    private Logger logger = LoggerFactory.getLogger(ItemController.class);
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        return ResponseEntity.ok(itemService.save(product));
    }

    @GetMapping()
    public ResponseEntity<List<Item>> getItems(@RequestParam(name = "name", required = false) String name,
                                               @RequestHeader(name = "token_request", required = false) String token){
        System.out.println(String.format("%s-%s", name, token));
        System.out.println("Este es el valor: " + value);
        return ResponseEntity.ok(itemService.getAll());
    }

    @CircuitBreaker(name = "items", fallbackMethod = "defaultMethod")
    @GetMapping("/show/{id}/{amount}")
    public ResponseEntity<Item> showItem(@PathVariable Long id, @PathVariable Integer amount){
        return ResponseEntity.ok(itemService.getById(id,amount));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id){
        itemService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Item> defaultMethod(Long id, Integer amount, Throwable e){
        Item item = new Item();
        Product product = new Product();
        product.setId(id);
        product.setName("Camera Sony");
        product.setPrice(100D);
        product.setCreateAt(Date.from(Instant.now()));
        item.setProduct(product);
        item.setAmount(amount);

        return ResponseEntity.ok(item);
    }

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }
}
