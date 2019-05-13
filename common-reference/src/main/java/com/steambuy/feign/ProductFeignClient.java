package com.steambuy.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "product-service")
public interface ProductFeignClient {

}
