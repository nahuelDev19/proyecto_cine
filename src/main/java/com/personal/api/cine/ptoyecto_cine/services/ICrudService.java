package com.personal.api.cine.ptoyecto_cine.services;

public interface ICrudService <RQ,RS,ID>{

    RS create(RQ rq);
    RS read(ID id);
    RS update(RQ rq);
    Void delete (ID id);


}
