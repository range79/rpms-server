package com.range.rpms.error.service.impl;

import com.range.rpms.error.domain.dto.ErrorResponse;
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
    private ErrorRepository errorRepository;
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
    public Page<Errors> findAll(int size, int page) {
     return errorRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<Errors> findServerErrors(int size, int page) {
        return null;
    }

    @Override
    public Page<Errors> findClientErrors(int size, int page) {
        return null;
    }
}
