package cl.bci.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.security.Provider;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.TestPropertySource;
import org.springframework.context.MessageSource;

import cl.bci.user.repository.UserRepository;
import cl.bci.user.repository.PhoneRepository;
import cl.bci.user.service.UserServiceImpl;
import cl.bci.user.dto.UserDTO;
import cl.bci.user.exception.BCIException;
import cl.bci.user.model.Phone;
import cl.bci.user.model.User;
import cl.bci.user.dto.RespuestaJSON;

@SpringBootTest
@TestPropertySource("classpath:messages.properties")
class UserServiceTests {

	private static final String idUser = "c2667cf9-78a1-4f68-aff1-bda90957e6fa";

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private PhoneRepository phoneRepository;

	@MockBean
	private MessageSource mensajes;

	@Test
	void crearUserTest() throws BCIException {
		//Arrage
		User user = new User();
		//Phone f = new Phone();
		UserDTO userDto = new UserDTO();
		userDto.setName("bci");
		userDto.setEmail("bci2@bci.cl");
		userDto.setPassword("Bgfdgfdg23dsfds");
		UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, phoneRepository, mensajes);
		when(userRepository.email(anyString())).thenReturn(Optional.empty());
    	when(userRepository.save(user)).thenReturn(any(User.class));
		//when(phoneRepository.save(f)).thenReturn(any(Phone.class));
		//Act
		RespuestaJSON r = userServiceImpl.crearUser(userDto);
		//Assert
		assertNotNull(r);
		assertEquals(((User)r.getAdicional()).getName(), userDto.getName());
	}

	@Test 
	void getUsersTest() {
		//Arrage
		User user = new User();
		user.setName("bci");
		User user2 = new User();
		User user3 = new User();
		List<User> usuarios = new ArrayList<User>();
		usuarios.add(user);
		usuarios.add(user2);
		usuarios.add(user3);
		UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, phoneRepository, mensajes);
		when(userRepository.findAll()).thenReturn(usuarios);
		//Act
		RespuestaJSON r = userServiceImpl.getUsers();
		//Assert
		assertNotNull(r);
		assertEquals(((ArrayList<UserDTO>)r.getAdicional()).get(0).getName(), usuarios.get(0).getName());
	}

}
