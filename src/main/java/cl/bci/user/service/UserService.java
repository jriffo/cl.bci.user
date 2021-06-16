package cl.bci.user.service;

import cl.bci.user.dto.RespuestaJSON;
import cl.bci.user.dto.UserDTO;

public interface UserService {

    RespuestaJSON crearUser(UserDTO user);

    RespuestaJSON getUsers();

    RespuestaJSON borraUser(int id);

    RespuestaJSON actualizaUser(UserDTO user);

    RespuestaJSON login(UserDTO user);
    
}