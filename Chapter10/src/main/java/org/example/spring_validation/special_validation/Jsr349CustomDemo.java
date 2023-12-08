package org.example.spring_validation.special_validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class Jsr349CustomDemo {

    private static final Logger logger = LoggerFactory.getLogger(Jsr349CustomDemo.class);

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        SingerValidationService singerValidationService = ctx.getBean(SingerValidationService.class);

        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setGenre(Genre.COUNTRY);
        singer.setGender(null);

        validateSinger(singer, singerValidationService);
        ctx.close();
    }

    private static void validateSinger(Singer singer, SingerValidationService singerValidationService) {

        Set<ConstraintViolation<Singer>> violations = singerValidationService.validateSinger(singer);

        listViolations(violations);
    }

    private static void listViolations(Set<ConstraintViolation<Singer>> violations) {
        logger.info("No. of violations: {}", violations.size());
        for (ConstraintViolation<Singer> violation : violations) {
            logger.info("Validation error for property: {} with value: {} with error message: {}",
                    violation.getPropertyPath(), violation.getInvalidValue(), violation.getMessage());
        }
    }
}
