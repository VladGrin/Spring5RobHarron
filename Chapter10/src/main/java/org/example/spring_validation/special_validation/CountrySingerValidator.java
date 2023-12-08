package org.example.spring_validation.special_validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CountrySingerValidator implements ConstraintValidator<CheckCountrySinger, Singer> {

    @Override
    public void initialize(CheckCountrySinger constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Singer singer, ConstraintValidatorContext constraintValidatorContext) {
        boolean result = true;
        if (singer.getGenre() != null && (singer.isCountrySinger() &&
                (singer.getLastName() == null || singer.getGender() == null))) {
            result = false;
        }
        return result;
    }

}
