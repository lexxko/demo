package com.example.feign;

import com.example.model.api.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "rs-module-feign-client", url = "${app.rs-module-url}")
public interface RsModuleFeignClient {
    @GetMapping(path = "get-rs")
    ResponseDto getResponse();
}
