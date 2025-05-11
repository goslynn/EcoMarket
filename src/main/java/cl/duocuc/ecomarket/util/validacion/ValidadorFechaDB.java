package cl.duocuc.ecomarket.util.validacion;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidadorFechaDB extends Validador implements ConstraintValidator<FechaDB, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return esFechaValida(s,"yyyyMMdd");
    }

}
