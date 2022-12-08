package com.example.dogadjaji213.controller;

import com.example.dogadjaji213.dto.GlobalResponseDto;
import com.example.dogadjaji213.dto.category.CategoryReqDto;
import com.example.dogadjaji213.dto.category.UpdateCategoryReqDto;
import com.example.dogadjaji213.model.Category;
import com.example.dogadjaji213.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
    private final CategoryService _categoryService;
    @GetMapping
    private ResponseEntity<GlobalResponseDto> Get(){
        var response = new GlobalResponseDto();
        try{
            var data = this._categoryService.getAll();
            response.setData(Optional.ofNullable(this._categoryService.getAll()));
            response.setPage(Optional.of(1));
            response.setCount(Optional.of(data.size()));
            return ResponseEntity.ok().body(response);
        }catch(Exception ex){
            response.setSuccess(false);
            response.setMessage("Error has occurred.".describeConstable());
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PostMapping
    private ResponseEntity<GlobalResponseDto> Post(@RequestBody CategoryReqDto category){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/location").toUriString());
        var response = new GlobalResponseDto();
        try{
            var createdCategory = this._categoryService.createNewCategory(category);
            if(createdCategory == null) throw new Exception("Creation failed.");
            response.setItem(Optional.of(createdCategory));
            response.setMessage("Created successfully".describeConstable());
            return ResponseEntity.created(uri).body(response);
        }catch(Exception ex){
            response.setSuccess(false);
            response.setMessage(ex.getMessage().describeConstable());
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<GlobalResponseDto> Patch(@PathVariable UUID id, @RequestBody UpdateCategoryReqDto categoryReqDto){
        var response = new GlobalResponseDto();
        try{
            var category = this._categoryService.updateCategory(id,categoryReqDto);
            if(category == null) throw new Exception("Category not found.");
            response.setItem(Optional.ofNullable(category));
            return ResponseEntity.ok().body(response);
        }catch(Exception ex){
            response.setSuccess(false);
            response.setMessage(ex.getMessage().describeConstable());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
