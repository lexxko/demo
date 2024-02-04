package com.example.service;

import com.example.feign.RsModuleFeignClient;
import com.example.model.api.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RqService {
    private final RsModuleFeignClient rsModuleFeignClient;

    @Autowired
    public RqService(RsModuleFeignClient rsModuleFeignClient) {
        this.rsModuleFeignClient = rsModuleFeignClient;
    }

    @Scheduled(initialDelay = 10, fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
    public void invoke() {
        final ResponseDto rs = rsModuleFeignClient.getResponse();
        log.info(rs.getResult());
    }
}
