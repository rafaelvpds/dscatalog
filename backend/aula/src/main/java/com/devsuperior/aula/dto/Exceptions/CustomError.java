package com.devsuperior.aula.dto.Exceptions;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomError {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
}
