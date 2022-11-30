package com.example.dogadjaji213.service.category;

import com.example.dogadjaji213.dto.CategoryReqDto;
import com.example.dogadjaji213.model.Category;
import com.example.dogadjaji213.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
