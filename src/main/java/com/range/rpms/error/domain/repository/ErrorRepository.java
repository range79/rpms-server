package com.range.rpms.error.domain.repository;

import com.range.rpms.error.domain.model.ErrorTypes;
import com.range.rpms.error.domain.model.Errors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.lang.model.type.ErrorType;
import java.util.UUID;

public interface ErrorRepository extends JpaRepository<Errors, UUID> {

    Page<Errors> findAll(Pageable pageable);
Page<Errors> findAllByErrorTypes(ErrorTypes errorType, Pageable pageable);

}
