package com.example.dogadjaji213.service.category;

import com.example.dogadjaji213.dto.CategoryReqDto;
import com.example.dogadjaji213.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface ICategory {
    List<Category> getAll();
    Category createNewCategory(CategoryReqDto category);
}
