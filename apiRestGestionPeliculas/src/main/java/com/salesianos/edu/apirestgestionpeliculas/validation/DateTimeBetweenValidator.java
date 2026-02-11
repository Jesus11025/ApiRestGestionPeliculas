package com.salesianos.edu.apirestgestionpeliculas.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;
import java.util.Date;

public class DateTimeBetweenValidator implements ConstraintValidator<DateTimeBetween, LocalDateTime> {

    private String strMinDate, strMaxDate;

    @Override
    public void initialize(DateTimeBetween constraintAnnotation) {
        this.strMinDate = constraintAnnotation.min();
        this.strMaxDate = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        LocalDateTime minDate = LocalDateTime.parse(strMinDate);
        LocalDateTime maxDate = LocalDateTime.parse(strMaxDate);

        return value != null && value.isAfter(minDate) && value.isBefore(maxDate);
    }
}
