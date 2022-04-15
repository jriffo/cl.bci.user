package cl.bci.user.service;

import cl.bci.user.dto.RespuestaJSON;
import cl.bci.user.dto.UserDTO;
import cl.bci.user.dto.LoginDTO;
import cl.bci.user.exception.BCIException;

public interface UserService {

    RespuestaJSON crearUser(UserDTO user) throws BCIException;

    RespuestaJSON getUsers();

    RespuestaJSON borraUser(String id);

    RespuestaJSON actualizaUser(UserDTO user);

    RespuestaJSON login(LoginDTO loginDTO);
    
}