/**
 *
 */
package com.microservices.bookmovie.premiereservice.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
@Documented
@Retention(RUNTIME)
@Target({FIELD, LOCAL_VARIABLE})
@Constraint(validatedBy = DateConstraintValidator.class)
public @interface DateValidator {
    String message() default "Must be of format yyyy-mm-dd ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
