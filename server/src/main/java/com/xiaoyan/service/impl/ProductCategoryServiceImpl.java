package com.xiaoyan.service.impl;

import com.xiaoyan.model.ProductCategory;
import com.xiaoyan.repository.ProductCategoryRepository;
import com.xiaoyan.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Override
    public List<ProductCategory> findByTypeIn(List<Integer> list) {
        return categoryRepository.findByCategoryTypeIn(list);
    }
}
