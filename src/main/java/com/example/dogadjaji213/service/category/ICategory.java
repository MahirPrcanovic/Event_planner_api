package com.example.dogadjaji213.service.category;

import com.example.dogadjaji213.dto.CategoryReqDto;
import com.example.dogadjaji213.dto.UpdateCategoryReqDto;
import com.example.dogadjaji213.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public interface ICategory {
    List<Category> getAll();
    Category createNewCategory(CategoryReqDto category);
    Category updateCategory(UUID id, UpdateCategoryReqDto category);
}
