package com.microservice.item.controller;

import com.microservice.item.data.model.Item;
import com.microservice.item.service.ItemService;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    private ItemService itemService;

    @GetMapping()
    public ResponseEntity<List<Item>> getItems(){
        return ResponseEntity.ok(itemService.getAll());
    }

    @GetMapping("{id}/{amout}")
    public ResponseEntity<Item> getItem(@PathVariable Long id, @PathVariable Integer amount){
        return ResponseEntity.ok(itemService.getById(id, amount));
    }

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }
}
