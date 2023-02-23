package com.example.demo.model;

import java.util.List;

public class CategoriaModel {
	
    private Long id;
    private String nombre;
    private String descripcion;
    List<ProductoModel> productos;
    
	public CategoriaModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CategoriaModel(Long id, String nombre, String descripcion, List<ProductoModel> productos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.productos = productos;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<ProductoModel> getProductos() {
		return productos;
	}
	public void setProductos(List<ProductoModel> productos) {
		this.productos = productos;
	}
	@Override
	public String toString() {
		return "CategoriaModel [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", productos="
				+ productos + "]";
	}

    
}