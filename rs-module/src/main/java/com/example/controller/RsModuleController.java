package com.example.controller;

import com.example.model.api.ResponseDto;
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

    @GetMapping(path = "get-nf-rs")
    public ResponseEntity<ResponseDto> getNfResponse() {
        return ResponseEntity.notFound().build();
    }
}
