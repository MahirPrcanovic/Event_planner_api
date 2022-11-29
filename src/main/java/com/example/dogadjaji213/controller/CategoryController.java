package com.example.dogadjaji213.controller;

import com.example.dogadjaji213.dto.CategoryDto;
import com.example.dogadjaji213.model.Category;
import com.example.dogadjaji213.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService _categoryService;
    @GetMapping
    private ResponseEntity<List<Category>> Get(){
        return ResponseEntity.ok().body(this._categoryService.getAll());
    }
    @PostMapping
    private ResponseEntity<Category> Post(@RequestBody CategoryDto category){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/location").toUriString());
        return ResponseEntity.created(uri).body(this._categoryService.createNewCategory(category));
    }
}
