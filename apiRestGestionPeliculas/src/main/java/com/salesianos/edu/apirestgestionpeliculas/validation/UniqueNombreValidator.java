package com.salesianos.edu.apirestgestionpeliculas.validation;

import ch.qos.logback.core.util.StringUtil;
import com.salesianos.edu.apirestgestionpeliculas.repository.PeliculaRepository;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class UniqueNombreValidator implements ConstraintValidator<UniqueNombre, String> {

    @Autowired
    private PeliculaRepository repo;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        return StringUtils.hasText(s) && !repo.existsByTitulo(s);
    }
}
