package com.xiaoyan.product.client;

import com.xiaoyan.product.common.DecreaseStockInput;
import com.xiaoyan.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product",fallback = ProductClient.ProductClientFallback.class )
public interface ProductClient {
    //暴露接口给订单服务使用-查询商品信息
    @PostMapping("/product/listForOrder")
    public  List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    //暴露接口给订单服务使用-减库存
    @PostMapping("/product/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> cartDTOList);

    @Component
    public static class ProductClientFallback implements  ProductClient{

        @Override
        public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
            return  null;
        }

        @Override
        public void decreaseStock(List<DecreaseStockInput> cartDTOList) {

        }
    }
}
