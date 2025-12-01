package com.range.rpms.error.service;

import com.range.rpms.error.domain.model.ErrorTypes;
import com.range.rpms.error.domain.model.Errors;
import com.range.rpms.error.dto.ErrorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ErrorService {
    ErrorResponse saveError(Errors errors);
    Page<Errors> findAll(Pageable pageable);
    Page<Errors> findErrorsByTypes(Pageable pageable, ErrorTypes errorTypes);

}
