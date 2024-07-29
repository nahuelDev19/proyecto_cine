package com.personal.api.cine.ptoyecto_cine.uitils.validaciones;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.personal.api.cine.ptoyecto_cine.models.request.FuncionRequest;

@Component
public class EnumValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return FuncionRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FuncionRequest fun = (FuncionRequest) target;
        if (fun.getSala() == null) {
            errors.rejectValue("sala", "SalaDeCine", "no puede ser nula o inválida");
        }
    }
}
