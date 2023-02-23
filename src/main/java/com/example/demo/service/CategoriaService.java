package com.example.demo.service;


import java.util.List;

import com.example.demo.entity.Categoria;
import com.example.demo.model.CategoriaModel;

public interface CategoriaService {
	public abstract CategoriaModel addCategoria(CategoriaModel categoriaModel);
	public abstract List<CategoriaModel> listCategorias();
	public abstract CategoriaModel findCategoria(Long id);
	public abstract CategoriaModel updateCategoria(CategoriaModel categoriaModel);
	public abstract void removeCategoria(Long id);
	public abstract void removeProductosByCategoria(long id);
	public abstract Categoria transform(CategoriaModel categoriaModel);
	public abstract CategoriaModel transform(Categoria categoria);
    
}
