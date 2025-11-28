package com.range.rpms.error.controller;

import com.range.rpms.error.api.ErrorsApi;
import com.range.rpms.error.domain.model.ErrorTypes;
import com.range.rpms.error.domain.model.Errors;
import com.range.rpms.error.service.ErrorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorsController implements ErrorsApi {
    private final ErrorService errorService;
    public ErrorsController(ErrorService errorService) {
        this.errorService = errorService;
    }


    @Override
    public Page<Errors> findAll(Pageable pageable) {
        return errorService.findAll(pageable);
    }

    @Override
    public Page<Errors> findErrorByType(Pageable pageable, ErrorTypes errorTypes) {
        return errorService.findErrorsByTypes(pageable, errorTypes);
    }
}
