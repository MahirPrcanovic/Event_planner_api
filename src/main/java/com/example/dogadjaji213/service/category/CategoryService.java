package com.example.dogadjaji213.service.category;

import com.example.dogadjaji213.dto.category.CategoryReqDto;
import com.example.dogadjaji213.dto.category.UpdateCategoryReqDto;
import com.example.dogadjaji213.model.Category;
import com.example.dogadjaji213.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryService implements ICategory{
    private final CategoryRepository _categoryRepository;
    @Override
    public List<Category> getAll() {
        return this._categoryRepository.findAll();
    }

    @Override
    public Category createNewCategory(CategoryReqDto category) {
        Category dbCategory = new Category(category.getName(),category.getIconUrl());
        Category categoryRes = this._categoryRepository.save(dbCategory);
        return categoryRes;
    }
    @Override
    public Category updateCategory(UUID id, UpdateCategoryReqDto categoryReqDto) {
        Optional<Category> category = this._categoryRepository.findById(id);
        if(category == null) throw new IllegalStateException("Category not existing.");
        if(category.isPresent()){
            if(categoryReqDto.getName().isPresent()){
                category.get().setName(categoryReqDto.getName().get());
            }
            if(categoryReqDto.getIconUrl().isPresent()){
                category.get().setIconUrl(categoryReqDto.getIconUrl().get());
            }
            return this._categoryRepository.save(category.get());
        }
        return null;
    }

    @Override
    public Category getSingle(UUID id) {
        return this._categoryRepository.findById(id).get();
    }
}
