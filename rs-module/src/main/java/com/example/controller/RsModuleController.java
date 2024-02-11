package com.example.controller;

import com.example.model.api.ResponseDto;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "rs")
public class RsModuleController extends BaseController {
    @GetMapping(path = "get-rs")
    public ResponseDto getResponse() {
        return new ResponseDto(0, "Success");
    }

    /**
     * Returns 404 response (NOT_FOUND)
     */
    @GetMapping(path = "get-nf-rs")
    public ResponseEntity<ResponseDto> getNfResponse() {
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "sd/get-rs")
    public ResponseDto getSdResponse() {
        return new ResponseDto(0, "Success. Service discovery is working");
    }

    /**
     * Returns timeout
     */
    @SneakyThrows
    @GetMapping(path = "get-timeout")
    public ResponseEntity<ResponseDto> getTimeout() {
        Thread.sleep(10000);
        return ResponseEntity.ok().build();
    }
}
