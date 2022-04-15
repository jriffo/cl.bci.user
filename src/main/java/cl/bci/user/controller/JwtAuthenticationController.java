package cl.bci.user.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;

import cl.bci.user.dto.LoginDTO;
import cl.bci.user.dto.RespuestaJSON;
import cl.bci.user.dto.UserDTO;
import cl.bci.user.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api")
public class JwtAuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource mensajes;

	@Value("${jwt.secret}")
	private String secret;

	@Value("${login.username}")
	private String loginName;

	@Value("${login.password}")
	private String loginPassword;

	@Autowired
	private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<RespuestaJSON> login(@Valid @RequestBody LoginDTO loginDTO) {
        try {
			authenticate(loginDTO.getName(), loginDTO.getPassword());
            loginDTO.setToken(getJWTToken(loginDTO.getName()));
            return new ResponseEntity<>(userService.login(loginDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RespuestaJSON(RespuestaJSON.EstadoType.ERROR.getRespuestaJSONS(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String getJWTToken(String username) {       
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
					secret.getBytes()).compact();

		return "Bearer " + token;
	}

	private void authenticate(String name, String password) throws Exception {
		if (!name.equals(loginName) || !password.equals(loginPassword)) {
			throw new BadCredentialsException(mensajes.getMessage("login.invalid-credentials", null, LocaleContextHolder.getLocale()));
		}
		/*
		try {
			//authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}*/
	}

}