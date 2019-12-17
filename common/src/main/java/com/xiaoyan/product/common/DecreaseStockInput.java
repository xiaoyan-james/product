package com.xiaoyan.product.common;

import lombok.Data;

@Data
public class DecreaseStockInput {
    /**
     * 商品id
     */
    private String productId;

    /**
     * 购买数量
     */
    private Integer productQuantity;

    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
