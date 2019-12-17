package com.xiaoyan.service;

import com.xiaoyan.ProductApplicationTests;
import com.xiaoyan.model.ProductInfo;
import com.xiaoyan.product.common.DecreaseStockInput;
import com.xiaoyan.product.common.ProductInfoOutput;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductServiceImplTest extends ProductApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() {
        List<ProductInfo> list = productService.findUpAll();
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void findList(){
        List<ProductInfoOutput> list=productService.findList(Arrays.asList("157875196366160022","157875227953464068"));
        Assert.assertTrue(list.size()>0);
    }

    @Test
    public void  decreaseStock(){
        List<DecreaseStockInput> list=new ArrayList<>();
        list.add(new DecreaseStockInput("157875196366160022",4));
        list.add(new DecreaseStockInput("157875227953464068",2));
        productService.decreaseStock(list);
    }
}