package com.xiaoyan;

import com.xiaoyan.model.ProductCategory;
import com.xiaoyan.model.ProductInfo;
import com.xiaoyan.repository.ProductCategoryRepository;
import com.xiaoyan.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductApplicationTests {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Test
    public void findByProductStatus() {
        List<ProductInfo> list = productRepository.findByProductStatus(1);
        Assert.assertTrue(list.size() > 0);

    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> list = categoryRepository.findByCategoryTypeIn(Arrays.asList(1, 2));
        Assert.assertTrue(list.size() > 0);
    }
}
