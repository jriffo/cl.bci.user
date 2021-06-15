package cl.bci.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.bci.user.dto.RespuestaJSON;
import cl.bci.user.model.User;
import cl.bci.user.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource mensajes;

    @GetMapping(value = "/hola")
	public String hola(@RequestParam(value="nombre", defaultValue="BCI") String nombre) {
		return "Hola " + nombre + "!!";
	}

    @PostMapping("/user")
    public ResponseEntity<RespuestaJSON> crearUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.crearUser(user), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new RespuestaJSON(RespuestaJSON.EstadoType.ERROR.getRespuestaJSONS(), mensajes.getMessage("user.error", null, LocaleContextHolder.getLocale())), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<RespuestaJSON> getUsers() {
        try {
            return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RespuestaJSON(RespuestaJSON.EstadoType.ERROR.getRespuestaJSONS(), mensajes.getMessage("user.error", null, LocaleContextHolder.getLocale())), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<RespuestaJSON> borraUser(@PathVariable int id) {
        try {
            RespuestaJSON r = userService.borraUser(id);
            if (r.getMensaje().equals(mensajes.getMessage("user.no-encontrado", null, LocaleContextHolder.getLocale()))) {
                return new ResponseEntity<>(r, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(r, HttpStatus.ACCEPTED); 
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return new ResponseEntity<>(new RespuestaJSON(RespuestaJSON.EstadoType.ERROR.getRespuestaJSONS(), mensajes.getMessage("user.error", null, LocaleContextHolder.getLocale())), HttpStatus.INTERNAL_SERVER_ERROR);            
        }
    }

    @PutMapping("/user")
    public ResponseEntity<RespuestaJSON> actualizaUser(@RequestBody User user) {
        try {
            RespuestaJSON r = userService.actualizaUser(user);
            if (r.getMensaje().equals(mensajes.getMessage("user.no-encontrado", null, LocaleContextHolder.getLocale()))) {
                return new ResponseEntity<>(r, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(r, HttpStatus.ACCEPTED); 
        } catch (Exception e) {
            return new ResponseEntity<>(new RespuestaJSON(RespuestaJSON.EstadoType.ERROR.getRespuestaJSONS(), mensajes.getMessage("user.error", null, LocaleContextHolder.getLocale())), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}