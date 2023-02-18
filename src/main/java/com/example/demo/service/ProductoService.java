package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Producto;
import com.example.demo.model.CategoriaModel;
import com.example.demo.model.ProductoModel;

public interface ProductoService {
	public abstract Producto addProducto(ProductoModel productoModel);
	public abstract List<Producto> findProductosByCategoria(CategoriaModel categoriaModel);
	public abstract Producto findProducto(Long id);
	public abstract Producto updateProducto(ProductoModel productoModel);
	public abstract void removeProducto(Long id);
	public abstract Producto transform(ProductoModel productoModel);
	public abstract ProductoModel transform(Producto producto);
	
	
}
