package com.example.service;

import com.example.feign.RsModuleFeignClient;
import com.example.model.api.ResponseDto;
import com.example.model.exception.DemoAppException;
import com.example.servicediscovery.ServiceDiscoveryFeignProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RqService {
    private final Mode mode;
    private final RsModuleFeignClient rsModuleFeignClient;
    private final ServiceDiscoveryFeignProxy feignProxy;

    @Autowired
    public RqService(@Value("${app.mode:OK}") Mode mode,
                     RsModuleFeignClient rsModuleFeignClient,
                     ServiceDiscoveryFeignProxy feignProxy) {
        this.mode = mode;
        this.rsModuleFeignClient = rsModuleFeignClient;
        this.feignProxy = feignProxy;
    }

    @Scheduled(initialDelay = 10, fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
    public void invoke() {
        try {
            final Optional<ResponseDto> response;
            switch (mode) {
                case OK -> response = rsModuleFeignClient.getResponse();
                case NOT_FOUND -> response = rsModuleFeignClient.getNfResponse();
                case SERVICE_DISCOVERY -> response = feignProxy.getResponse();
                case TIMEOUT -> response = rsModuleFeignClient.getTimeout();
                default -> throw new DemoAppException();
            }
            response.ifPresentOrElse(rs -> log.info(rs.getResult()), () -> log.info("Result is empty"));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public enum Mode {
        OK,
        NOT_FOUND,
        SERVICE_DISCOVERY,
        TIMEOUT
    }

//    private static
}
