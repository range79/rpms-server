package com.range.rpms.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse<T> {

    private boolean success;
    private String message;
    private int statusCode;
    private T data;

}
