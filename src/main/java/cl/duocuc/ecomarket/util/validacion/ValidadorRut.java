package cl.duocuc.ecomarket.util.validacion;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidadorRut extends Validador implements ConstraintValidator<Rut, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return esRutValido(value);
    }

}
