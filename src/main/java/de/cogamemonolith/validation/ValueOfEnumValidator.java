package de.cogamemonolith.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * represents logical part for validation of enum annotated attributes with EnumValidation
 */
public class ValueOfEnumValidator implements ConstraintValidator<EnumValidation, CharSequence> {

    private List<String> acceptedValues;

    /**
     * saves values of the enum, which should later be compared with the value of the variable
     */
    @Override
    public void initialize(EnumValidation annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    /**
     * checks if a string has the same value as one of the enum's values
     */
    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return acceptedValues.contains(value.toString());
    }
}
