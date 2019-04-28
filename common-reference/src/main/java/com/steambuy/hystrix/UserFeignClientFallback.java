package com.steambuy.hystrix;

import com.steambuy.feign.UserFeignClient;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallback implements UserFeignClient {
    @Override
    public String queryUserById(Long id) {
        return "查询异常";
    }
}
