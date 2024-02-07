package com.example.service;

import com.example.feign.RsModuleFeignClient;
import com.example.model.api.ResponseDto;
import com.example.model.exception.DemoAppException;
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

    @Autowired
    public RqService(@Value("${app.mode:OK}") Mode mode,
                     RsModuleFeignClient rsModuleFeignClient) {
        this.mode = mode;
        this.rsModuleFeignClient = rsModuleFeignClient;
    }

    @Scheduled(initialDelay = 10, fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
    public void invoke() {
        final Optional<ResponseDto> response;
        switch (mode) {
            case OK -> response = rsModuleFeignClient.getResponse();
            case NOT_FOUND -> response = rsModuleFeignClient.getNfResponse();
            default -> throw new DemoAppException();
        }
        response.ifPresentOrElse(rs -> log.info(rs.getResult()), () -> log.info("Result is empty"));
    }

    public enum Mode {
        OK,
        NOT_FOUND
    }
}
