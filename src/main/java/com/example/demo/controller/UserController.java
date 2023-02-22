package com.example.demo.controller;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.model.ProductoModel;
import com.example.demo.service.ProductoService;
import com.example.demo.serviceImpl.UserServiceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	@Qualifier("userService")
	private UserServiceImpl userService;
	
	@Autowired
	@Qualifier("productoService")
	private ProductoService productoService;

	@PostMapping("/login")
	public com.example.demo.entity.User login(@RequestParam("user") String username,
			@RequestParam("password") String pwd) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, pwd));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		com.example.demo.entity.User usuario = userService.findUsuario(username);
		String token = getJWTToken(username);
		usuario.setToken(token);
		return usuario;

	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts.builder().setId("softtekJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
	@PostMapping("/register")
	public User saveUser(@RequestBody User user) {
		return userService.registrar(user);
	}
	
	@GetMapping("/users/{idUser}/favoritos")
	public List<ProductoModel> obtenerFavoritos(@PathVariable(value = "idUser") long idUser) {
	    User user = userService.findUser(idUser);
	    return user.getFavoritos().stream().map(x -> productoService.transform(x)).collect(Collectors.toList());
	}

}
