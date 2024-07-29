package com.personal.api.cine.ptoyecto_cine.uitils.validaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.personal.api.cine.ptoyecto_cine.models.request.FuncionRequest;
import com.personal.api.cine.ptoyecto_cine.repositorys.PeliculaRepository;

/**
 * Validador personalizado para validar objetos de tipo FuncionRequest.
 * Este validador comprueba que el campo pelicula en FuncionRequest no sea nulo,
 * no esté vacío y que la película exista en el repositorio.
 */
@Component
public class PeliculaRequestValidator implements Validator {

    @Autowired
    private PeliculaRepository pel;

    /**
     * Determina si el validador soporta la clase dada.
     *
     * @param clazz la clase a comprobar
     * @return true si la clase es compatible con FuncionRequest, de lo contrario false
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return FuncionRequest.class.isAssignableFrom(clazz);
    }

    /**
     * Realiza la validación de un objeto FuncionRequest.
     * Valida que el campo pelicula no sea nulo, que el título de la película no esté vacío,
     * y que la película exista en el repositorio.
     *
     * @param target el objeto a validar
     * @param errors contenedor de errores para la validación
     */
    @Override
    public void validate(Object target, Errors errors) {
        FuncionRequest fun = (FuncionRequest) target;

        // Validar que el campo pelicula no sea nulo
        if (fun.getPelicula() == null) {
            errors.rejectValue("pelicula", null, "La película es requerida");
        } else {
            // Validar que el título de la película no esté vacío
            if (fun.getPelicula().getTitulo() == null || fun.getPelicula().getTitulo().isBlank()) {
                errors.rejectValue("pelicula.titulo", null, "El título de la película es requerido");
            } else {
                // Verificar si la película existe en los registros
                boolean peliculaEntity = pel.existsByTitulo(fun.getPelicula().getTitulo());
                if (!peliculaEntity) {
                    errors.rejectValue("pelicula", null, "La película no existe en nuestros registros");
                }
            }
        }
    }
}
