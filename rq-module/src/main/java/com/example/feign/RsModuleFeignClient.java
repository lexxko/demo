package com.example.feign;

import com.example.model.api.ResponseDto;
import com.example.model.exception.DemoAppException;
import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@FeignClient(
        name = "rs-module-feign-client",
        url = "${app.rs-module-url}",
        fallbackFactory = RsModuleFeignClient.RsModuleFeignClientFallback.class)
public interface RsModuleFeignClient {
    @GetMapping(path = "get-rs")
    Optional<ResponseDto> getResponse();

    /**
     * Returns 404 response (NOT_FOUND)
     */
    @GetMapping(path = "get-nf-rs")
    Optional<ResponseDto> getNfResponse();

    @Component
    class RsModuleFeignClientFallback implements FallbackFactory<RsModuleFeignClient> {
        @Override
        public RsModuleFeignClient create(Throwable cause) {
            return new RsModuleFeignClient() {
                @Override
                public Optional<ResponseDto> getResponse() {
                    throw new DemoAppException(cause.getMessage());
                }

                @Override
                public Optional<ResponseDto> getNfResponse() {
                    if (cause instanceof FeignException.NotFound) {
                        return Optional.empty();
                    } else if (cause instanceof FeignException) {
                        throw (FeignException) cause;
                    } else {
                        throw new DemoAppException(cause.getMessage());
                    }
                }
            };
        }
    }
}
