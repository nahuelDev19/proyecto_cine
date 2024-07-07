package com.personal.api.cine.ptoyecto_cine.services;

import com.personal.api.cine.ptoyecto_cine.models.request.UsuarioRequest;
import com.personal.api.cine.ptoyecto_cine.models.responses.UsuarioResponse;

public interface IUsuarioService extends ICrudService<UsuarioRequest,UsuarioResponse,Long>{

    @Override
    default UsuarioResponse create(UsuarioRequest rq) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    default Void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    default UsuarioResponse read(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @Override
    default UsuarioResponse update(UsuarioRequest rq) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    

}
