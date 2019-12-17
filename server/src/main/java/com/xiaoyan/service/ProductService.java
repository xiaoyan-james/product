package com.xiaoyan.service;

import com.xiaoyan.model.ProductInfo;
import com.xiaoyan.product.common.DecreaseStockInput;
import com.xiaoyan.product.common.ProductInfoOutput;

import java.util.List;

public interface ProductService {

    List<ProductInfo> findUpAll();

    List<ProductInfoOutput> findList(List<String> productIdList);

    void decreaseStock(List<DecreaseStockInput> cartDTOList);
}
