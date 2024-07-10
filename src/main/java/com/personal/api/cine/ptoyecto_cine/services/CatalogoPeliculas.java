package com.personal.api.cine.ptoyecto_cine.services;

import org.springframework.data.domain.Page;

public interface CatalogoPeliculas <RQ,RS,ID>{

    Page<RS> paginado(RQ rq);
    RS create(RQ rq);
    RS read(ID id);
    RS update(RQ rq,ID id);
    void delete (ID id);
}
