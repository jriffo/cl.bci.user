package cl.bci.user.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.bci.user.dto.RespuestaJSON;
import cl.bci.user.model.Phone;
import cl.bci.user.model.User;
import cl.bci.user.repository.PhoneRepository;
import cl.bci.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private MessageSource mensajes;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public RespuestaJSON crearUser(User user) {
        user.setCreated(new Timestamp(new Date().getTime()));
        user.setLast_login(new Timestamp(new Date().getTime()));
        user.setIsactive(true);
        userRepository.save(user);
        return new RespuestaJSON(RespuestaJSON.EstadoType.OK.getRespuestaJSONS(), mensajes.getMessage("user.creado", null, LocaleContextHolder.getLocale()), user);
    }

    @Override
    public RespuestaJSON getUsers() {
        List<User> t = userRepository.findAll();        
        return new RespuestaJSON(RespuestaJSON.EstadoType.OK.getRespuestaJSONS(), mensajes.getMessage("ok", null, LocaleContextHolder.getLocale()), t);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public RespuestaJSON borraUser(int id) {
        Optional<User> t = userRepository.findById(id);
        if (t.isPresent()) {
            User u = t.get();
            List<Phone> p = u.getPhones();
            p.forEach((final Phone phone) -> phoneRepository.delete(phone));  
            userRepository.delete(u);
            return new RespuestaJSON(RespuestaJSON.EstadoType.OK.getRespuestaJSONS(), mensajes.getMessage("user.eliminado", null, LocaleContextHolder.getLocale()), u);
        }   
        return new RespuestaJSON(RespuestaJSON.EstadoType.ERROR.getRespuestaJSONS(), mensajes.getMessage("user.no-encontrado", null, LocaleContextHolder.getLocale()));       
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public RespuestaJSON actualizaUser(User user) {
        Optional<User> t = userRepository.findById(user.getId());
        if (t.isPresent()) {
            User u = t.get();
            u.setEmail(user.getEmail());
            u.setIsactive(user.getIsactive());
            u.setLast_login(new Timestamp(new Date().getTime()));
            u.setModified(new Timestamp(new Date().getTime()));
            u.setName(user.getName());
            u.setPassword(user.getPassword());            
            return new RespuestaJSON(RespuestaJSON.EstadoType.OK.getRespuestaJSONS(), mensajes.getMessage("user.actualizado", null, LocaleContextHolder.getLocale()), u);
        }   
        return new RespuestaJSON(RespuestaJSON.EstadoType.ERROR.getRespuestaJSONS(), mensajes.getMessage("no-encontrado", null, LocaleContextHolder.getLocale()));       
    }
    
}