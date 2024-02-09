package com.example.servicediscovery;

import com.example.feign.RsModuleFeignClient;
import com.example.model.api.ResponseDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@Component
public class ServiceDiscoveryFeignProxy {
    // Service discovery key for RsService
    private static final String SD_RS_SERVICE_KEY = "sd_rs_service_key";
    private static final Map<String, URI> urlMap = Map.of(SD_RS_SERVICE_KEY, URI.create("http://localhost:8081/rs/sd"));

    private final RsModuleFeignClient feignClient;

    @Autowired
    public ServiceDiscoveryFeignProxy(RsModuleFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @NotNull
    public Optional<ResponseDto> getResponse() {
        return feignClient.getResponse(findUri(SD_RS_SERVICE_KEY));
    }

    @SuppressWarnings("SameParameterValue")
    @NotNull
    private URI findUri(@NotNull String key) {
        return urlMap.get(key);
    }

}
