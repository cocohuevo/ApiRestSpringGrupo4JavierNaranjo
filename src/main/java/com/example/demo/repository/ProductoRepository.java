package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Serializable> {
	List<Producto> findByCategoria(Categoria categoria);
	
}
