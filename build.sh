#!/usr/bin/env bash

mvn clean package -Dmaven.test.skip=true

docker build -t registry.cn-beijing.aliyuncs.com/springcloud-xiaoyan/product .

#!docker tag 5878a2b3ccfc registry.cn-beijing.aliyuncs.com/springcloud-xiaoyan/product

#!docker push registry.cn-beijing.aliyuncs.com/springcloud-xiaoyan/product
