package com.range.rpms.error.controller;

import com.range.rpms.error.api.ErrorsApi;
import com.range.rpms.error.domain.model.Errors;
import com.range.rpms.error.service.ErrorService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorsController implements ErrorsApi {
    private final ErrorService errorService;
    public ErrorsController(ErrorService errorService) {
        this.errorService = errorService;
    }

    @Override
    public ResponseEntity<Page<Errors>> findAll(int size, int page) {
        return ResponseEntity.ok(errorService.findAll(size, page));
    }

    @Override
    public ResponseEntity<Page<Errors>> findServerErrors(int size, int page) {
        return ResponseEntity.ok(errorService.findServerErrors(size, page));
    }

    @Override
    public ResponseEntity<Page<Errors>> findClientErrors(int size, int page) {
        return ResponseEntity.ok(errorService.findClientErrors(size, page));
    }
}
