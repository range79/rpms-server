package com.range.rpms.error.api;

import com.range.rpms.error.domain.model.Errors;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("${app.base-path}/errors")
public interface ErrorsApi {

    @GetMapping("/all")
    ResponseEntity<Page<Errors>> findAll(@RequestParam(defaultValue ="10" ) int size,
                                         @RequestParam(defaultValue = "0") int page );
    @GetMapping("/server")
    Page<Errors> findServerErrors(@RequestParam(defaultValue ="10" ) int size,
                                  @RequestParam(defaultValue = "0") int page);
    @GetMapping("/client")
    Page<Errors> findClientErrors(@RequestParam(defaultValue ="10" ) int size,
                                  @RequestParam(defaultValue = "0") int page);
}
