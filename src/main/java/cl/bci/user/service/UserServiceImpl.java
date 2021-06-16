package cl.bci.user.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.bci.user.dto.PhoneDTO;
import cl.bci.user.dto.RespuestaJSON;
import cl.bci.user.dto.UserDTO;
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
    public RespuestaJSON login(UserDTO user) {
        User u = userRepository.name(user.getName());
        if (u==null) {
            return crearUser(user);
        }
        return new RespuestaJSON(RespuestaJSON.EstadoType.OK.getRespuestaJSONS(), mensajes.getMessage("ok", null, LocaleContextHolder.getLocale()), user);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public RespuestaJSON crearUser(UserDTO user) {
        User u = new User();
        BeanUtils.copyProperties(user, u);
        u.setCreated(new Timestamp(new Date().getTime()));
        u.setLast_login(new Timestamp(new Date().getTime()));
        u.setIsactive(true);         
        userRepository.save(u);
        List<PhoneDTO> p = user.getPhones();
        p.forEach((final PhoneDTO phone) -> { 
            Phone f = new Phone();
            BeanUtils.copyProperties(phone, f);
            f.setUser(u); 
            phoneRepository.save(f); 
        });
        return new RespuestaJSON(RespuestaJSON.EstadoType.OK.getRespuestaJSONS(), mensajes.getMessage("user.creado", null, LocaleContextHolder.getLocale()), user);
    }

    @Override
    public RespuestaJSON getUsers() {
        List<User> t = userRepository.findAll();
        List<UserDTO> tDto = new ArrayList<UserDTO>();
        t.forEach((final User user) -> { 
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            userDTO.setPhones(user.getPhones().stream().map((Phone phone) -> {
                PhoneDTO phoneDTO = new PhoneDTO();
                BeanUtils.copyProperties(phone, phoneDTO);
                return phoneDTO;
            }).collect(Collectors.toList()));
            tDto.add(userDTO);            
        });
        return new RespuestaJSON(RespuestaJSON.EstadoType.OK.getRespuestaJSONS(), mensajes.getMessage("ok", null, LocaleContextHolder.getLocale()), tDto);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public RespuestaJSON borraUser(int id) {
        Optional<User> t = userRepository.findById(id);
        if (t.isPresent()) {
            User u = t.get();
            UserDTO user = new UserDTO();
            BeanUtils.copyProperties(u, user);
            List<Phone> p = u.getPhones();
            p.forEach((final Phone phone) -> phoneRepository.delete(phone));  
            userRepository.delete(u);
            return new RespuestaJSON(RespuestaJSON.EstadoType.OK.getRespuestaJSONS(), mensajes.getMessage("user.eliminado", null, LocaleContextHolder.getLocale()), user);
        }   
        return new RespuestaJSON(RespuestaJSON.EstadoType.ERROR.getRespuestaJSONS(), mensajes.getMessage("user.no-encontrado", null, LocaleContextHolder.getLocale()));       
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public RespuestaJSON actualizaUser(UserDTO user) {
        Optional<User> t = userRepository.findById(user.getId());
        if (t.isPresent()) {
            User u = t.get();
            u.setEmail(user.getEmail()!=null ? user.getEmail() : u.getEmail());
            u.setIsactive(user.getIsactive()!=null ? user.getIsactive() : u.getIsactive());
            u.setLast_login(new Timestamp(new Date().getTime()));
            u.setModified(new Timestamp(new Date().getTime()));
            u.setName(user.getName());
            u.setPassword(user.getPassword());
            return new RespuestaJSON(RespuestaJSON.EstadoType.OK.getRespuestaJSONS(), mensajes.getMessage("user.actualizado", null, LocaleContextHolder.getLocale()), user);
        }   
        return new RespuestaJSON(RespuestaJSON.EstadoType.ERROR.getRespuestaJSONS(), mensajes.getMessage("no-encontrado", null, LocaleContextHolder.getLocale()));       
    }
    
}