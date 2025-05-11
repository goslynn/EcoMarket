package cl.duocuc.ecomarket.util.validacion;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidadorTelefono extends Validador implements ConstraintValidator<Telefono, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return esNumeroTelefonicoValido(value);
    }
}
