package de.cogamemonolith.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * represents annotation for enum validation by spring-boot-starter-validation
 *
 * Usage: annotate a string, which should be validated with enum pattern is needed
 *        @EnumValidation(enumClass = MyEnum.class, message = "my message")
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = ValueOfEnumValidator.class)
public @interface EnumValidation {

    Class<? extends Enum<?>> enumClass();
    String message() default "must be any of enum {enumClass}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
