package com.xiaoyan.service.impl;

import com.xiaoyan.enums.ProductStatusEnum;
import com.xiaoyan.enums.ResultEnum;
import com.xiaoyan.exception.ProductException;
import com.xiaoyan.model.ProductInfo;
import com.xiaoyan.product.common.DecreaseStockInput;
import com.xiaoyan.product.common.ProductInfoOutput;
import com.xiaoyan.repository.ProductRepository;
import com.xiaoyan.service.ProductService;
import com.xiaoyan.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        return productRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoOutput> findList(List<String> productIdList) {
        List<ProductInfoOutput> list=productRepository.findByProductIdIn(productIdList).stream()
                .map(e->{
                    ProductInfoOutput output=new ProductInfoOutput();
                    BeanUtils.copyProperties(e,output);
                    return  output;
                }).collect(Collectors.toList());
        return list;
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputs) {
            List<ProductInfo> productInfoList=this.decreaseStockProcess(decreaseStockInputs);
            //先扣完库存后，再发送消息
            List<ProductInfoOutput>  productInfoOutputList=productInfoList.stream().map(e -> {
                ProductInfoOutput output=new ProductInfoOutput();
                BeanUtils.copyProperties(e,output);
                return  output;
            }).collect(Collectors.toList());
            //发送mq消息
            log.info("发送消息队列（扣库存成功）{}的消息：{}","productInfo",JsonUtil.toJson(productInfoOutputList));
            amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));
    }

    //扣库存
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputs) {
        List<ProductInfo> list=new ArrayList<>();
        for (DecreaseStockInput decreaseStockInput: decreaseStockInputs){
            Optional<ProductInfo> productInfoOptional=productRepository.findById(decreaseStockInput.getProductId());
            //判断商品是否存在
            if(!productInfoOptional.isPresent()){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo=productInfoOptional.get();
            //判断库存是否足够
            Integer result=productInfo.getProductStock()-decreaseStockInput.getProductQuantity();
            if(result<0){
                throw new  ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productRepository.save(productInfo);
            list.add(productInfo);
        }
        return  list;
    }
}
