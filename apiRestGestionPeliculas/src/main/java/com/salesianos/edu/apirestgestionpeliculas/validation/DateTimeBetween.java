package com.salesianos.edu.apirestgestionpeliculas.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueNombreValidator.class)
@Documented
public @interface DateTimeBetween {

    String message() default "La fecha de estreno debe estar entre los rangos permitidos";

    Class<?> [] groups() default {};

    Class<? extends Payload> [] payload() default {};

    String min();
    String max();
}
