package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Producto;
import com.example.demo.model.CategoriaModel;
import com.example.demo.model.ProductoModel;

public interface ProductoService {
	public abstract ProductoModel addProducto(ProductoModel productoModel);
	public abstract List<ProductoModel> listProductos();
	public abstract List<ProductoModel> findProductosByCategoria(CategoriaModel categoriaModel);
	public abstract ProductoModel findProducto(Long id);
	public abstract ProductoModel updateProducto(ProductoModel productoModel);
	public abstract void removeProducto(Long id);
	public abstract Producto transform(ProductoModel productoModel);
	public abstract ProductoModel transform(Producto producto);
	
	
}
