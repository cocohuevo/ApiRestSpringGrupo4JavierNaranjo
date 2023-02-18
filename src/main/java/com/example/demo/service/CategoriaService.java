package com.example.demo.service;


import com.example.demo.entity.Categoria;
import com.example.demo.model.CategoriaModel;

public interface CategoriaService {
	public abstract Categoria addCategoria(CategoriaModel categoriaModel);
	public abstract Categoria findCategoria(Long id);
	public abstract Categoria updateCategoria(CategoriaModel categoriaModel);
	public abstract void removeCategoria(Long id);
	public abstract void removeProductosByCategoria(CategoriaModel categoriaModel);
	public abstract Categoria transform(CategoriaModel categoriaModel);
	public abstract CategoriaModel transform(Categoria categoria);
    
}
