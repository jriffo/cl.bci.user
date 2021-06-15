package cl.bci.user.service;

import cl.bci.user.dto.RespuestaJSON;
import cl.bci.user.model.User;

public interface UserService {

    RespuestaJSON crearUser(User user);

    RespuestaJSON getUsers();

    RespuestaJSON borraUser(int id);

    RespuestaJSON actualizaUser(User user);
    
}