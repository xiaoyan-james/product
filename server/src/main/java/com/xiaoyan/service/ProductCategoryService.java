package com.xiaoyan.service;

import com.xiaoyan.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategory> findByTypeIn(List<Integer> list);
}
