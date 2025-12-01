package com.range.rpms.error.api;

import com.range.rpms.error.domain.model.ErrorTypes;
import com.range.rpms.error.domain.model.Errors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("${app.base-path}/errors")
public interface ErrorsApi {

    @GetMapping("/all")
    Page<Errors> findAll(Pageable pageable);
    @GetMapping("/{errorTypes}")
    Page<Errors> findErrorByType(Pageable pageable, @PathVariable ErrorTypes errorTypes);

}
