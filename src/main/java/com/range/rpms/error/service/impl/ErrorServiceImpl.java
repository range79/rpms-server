package com.range.rpms.error.service.impl;

import com.range.rpms.error.dto.ErrorResponse;
import com.range.rpms.error.domain.model.ErrorTypes;
import com.range.rpms.error.domain.model.Errors;
import com.range.rpms.error.domain.repository.ErrorRepository;
import com.range.rpms.error.service.ErrorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ErrorServiceImpl implements ErrorService {
    private final ErrorRepository errorRepository;
    public ErrorServiceImpl(ErrorRepository errorRepository) {
        this.errorRepository = errorRepository;
    }
    @Override
    public ErrorResponse saveError(Errors errors) {
      Errors error =    errorRepository.save(errors);
   return ErrorResponse.builder()
           .detail(error.getDetail())
           .message(error.getMessage())
           .timestamp(error.getTimestamp()).build();
    }

    @Override
    public Page<Errors> findAll(Pageable pageable) {
        return errorRepository.findAll(pageable);
    }

    @Override
    public Page<Errors> findErrorsByTypes(Pageable pageable, ErrorTypes errorTypes) {
        return errorRepository.findAllByErrorType(errorTypes, pageable);
    }


}
