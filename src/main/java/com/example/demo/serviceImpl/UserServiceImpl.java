package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.example.demo.entity.User usuario = usuarioRepository.findByUsername(username);

		UserBuilder builder = null;

		if (usuario != null) {
			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(usuario.getPassword());
			builder.authorities(new SimpleGrantedAuthority(usuario.getRole()));

		} else
			throw new UsernameNotFoundException("Usuario no encontrado");
		return builder.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public com.example.demo.entity.User registrar(com.example.demo.entity.User user) {
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		user.setEnable(true);
		user.setRole("ROLE_USER");
		return usuarioRepository.save(user);
	}

	public int activar(String username) {
		int a = 0;
		com.example.demo.entity.User u = usuarioRepository.findByUsername(username);
		com.example.demo.entity.User user = new com.example.demo.entity.User();
		user.setPassword(passwordEncoder().encode(u.getPassword()));
		user.setUsername(u.getUsername());
		user.setId(u.getId());

		if (u.isEnable() == false) {
			user.setEnable(true);
			a = 1;
		} else {
			user.setEnable(false);
			a = 0;
		}
		user.setRole(u.getRole());

		usuarioRepository.save(user);
		return a;
	}

	public void deleteUser(String username) throws Exception {
		com.example.demo.entity.User u = usuarioRepository.findByUsername(username);
		usuarioRepository.delete(u);
	}

	public List<com.example.demo.entity.User> listAllUsuarios() {
		return usuarioRepository.findAll().stream().collect(Collectors.toList());
	}

	public com.example.demo.entity.User findUsuario(String username) {

		return usuarioRepository.findByUsername(username);
	}
	public com.example.demo.entity.User findUserByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
