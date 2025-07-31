package com.range.rpms.error.service;

import com.range.rpms.error.dto.ErrorResponse;
import com.range.rpms.error.domain.model.Errors;
import org.springframework.data.domain.Page;

public interface ErrorService {
    ErrorResponse saveError(Errors errors);
    Page<Errors> findAll(int size, int page);
    Page<Errors> findServerErrors(int size, int page);
    Page<Errors> findClientErrors(int size, int page);
}
