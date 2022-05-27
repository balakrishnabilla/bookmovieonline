/** */
package com.microservices.bookmovie.premiereservice.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConstraintValidator implements ConstraintValidator<DateValidator, String> {
  static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @Override
  public boolean isValid(String date, ConstraintValidatorContext context) {
    try {
      LocalDate.parse(date, dateFormatter);
    } catch (RuntimeException ex) {
      return false;
    }

    return true;
  }
}
