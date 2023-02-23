package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	@Column(name = "password", nullable = false)
	private String password;
	private boolean enable;
	private String role;
	private String token;
	@ManyToMany
	@JoinTable(name = "usuario_favorito",
	    joinColumns = @jakarta.persistence.JoinColumn(name = "usuario_id"),
	    inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "producto_id"))
	private List<Producto> favoritos;

	public User(long id, String username, String password, boolean enable, String role, String token, List<Producto> favoritos) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enable = enable;
		this.role = role;
		this.token = token;
		this.favoritos = favoritos;
	}

	public User() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	public void agregarFavorito(Producto producto) {
        this.favoritos.add(producto);
    }

    public List<Producto> getFavoritos() {
        return favoritos;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enable=" + enable + ", role="
				+ role + ", token=" + token + "]";
	}
	
}
