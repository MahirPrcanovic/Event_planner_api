package com.example.dogadjaji213.service.category;

import com.example.dogadjaji213.dto.CategoryDto;
import com.example.dogadjaji213.dto.LocationDto;
import com.example.dogadjaji213.model.Category;
import com.example.dogadjaji213.model.Location;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface ICategory {
    List<Category> getAll();
    Category createNewCategory(CategoryDto category);
}
