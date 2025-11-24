package com.example.appstore_service;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/items")
public class SampleController {

    private final Map<Integer, String> items = new ConcurrentHashMap<>();

    @GetMapping("/{id}")
    public String getItem(@PathVariable int id) {
        return items.getOrDefault(id, "Not Found");
    }

    @PostMapping
    public String addItem(@RequestParam int id, @RequestParam String name) {
        items.put(id, name);
        return "Added";
    }

    @PutMapping("/{id}")
    public String updateItem(@PathVariable int id, @RequestParam String name) {
        if (items.containsKey(id)) {
            items.put(id, name);
            return "Updated";
        }
        return "Not Found";
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable int id) {
        if (items.remove(id) != null) {
            return "Deleted";
        }
        return "Not Found";
    }

    @GetMapping("/all")
    public String getAllItem() {
        final Map<Integer, String> items = new ConcurrentHashMap<>();
        items.put(1, "Item_1");
        items.put(2, "Item_2");
        items.put(3, "Item_3");
        items.put(4, "Item_4");
        items.put(5, "Item_5");

        return items.toString();
    }

    @GetMapping("/welcome")
    public String welcomeMessage() {
        return "Welcome to the JAVA Appstore Service!";
    }
}