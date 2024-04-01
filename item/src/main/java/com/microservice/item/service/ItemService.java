package com.microservice.item.service;

import com.microservice.item.data.model.Item;
import com.microservice.item.data.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private RestTemplate restTemplate;

    public List<Item> getAll(){
        List<Product> productList = Arrays.asList(restTemplate
                .getForObject("http://product-service/product", Product[].class));

        return productList.stream()
                .map(product -> new Item(product, 1))
                .collect(Collectors.toList());
    }

    public Item getById(Long id, Integer amount){
        Map<String, String> pathVariable = new HashMap<>();
        pathVariable.put("id", id.toString());
        Product product = restTemplate.getForObject("http://product-service/product/{id}", Product.class, id);
        return new Item(product, amount);
    }


    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
