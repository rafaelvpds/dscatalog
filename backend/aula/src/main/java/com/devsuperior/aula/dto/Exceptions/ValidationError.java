package com.devsuperior.aula.dto.Exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError {

    private List<FieldMenssage> errors = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
        // TODO Auto-generated constructor stub
    }

    public List<FieldMenssage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        errors.removeIf(x -> x.getFieldName().equals(fieldName));
        errors.add(new FieldMenssage(fieldName, message));
    }

}
