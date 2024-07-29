package com.personal.api.cine.ptoyecto_cine.services;

/**
 * Interfaz genérica para operaciones CRUD.
 * 
 * @param <RQ> Tipo de la solicitud de creación y actualización.
 * @param <RS> Tipo de la respuesta.
 * @param <ID> Tipo del identificador de la entidad.
 */
public interface ICrudService<RQ, RS, ID> {

    /**
     * Crea una nueva entidad.
     * 
     * @param rq La solicitud de creación.
     * @return La respuesta con los detalles de la entidad creada.
     */
    RS create(RQ rq);

    /**
     * Lee una entidad por su identificador.
     * 
     * @param id El identificador de la entidad.
     * @return La respuesta con los detalles de la entidad.
     */
    RS read(ID id);

    /**
     * Actualiza una entidad existente.
     * 
     * @param rq La solicitud de actualización con los nuevos datos.
     * @param id El identificador de la entidad a actualizar.
     * @return La respuesta con los detalles de la entidad actualizada.
     */
    RS update(RQ rq, ID id);

    /**
     * Elimina una entidad por su identificador.
     * 
     * @param id El identificador de la entidad a eliminar.
     */
    void delete(ID id);
}
