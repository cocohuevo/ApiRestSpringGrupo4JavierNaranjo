package com.example.demo.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.Producto;
import com.example.demo.model.CategoriaModel;
import com.example.demo.model.ProductoModel;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.ProductoService;

@Service("productoService")
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    @Qualifier("productoRepository")
    private ProductoRepository productoRepository;
    
    @Autowired
    @Qualifier("categoriaService")
    private CategoriaService categoriaService;

    public Producto addProducto(ProductoModel productoModel) {
        return productoRepository.save(transform(productoModel));
    }

    public List<Producto> findProductosByCategoria(CategoriaModel categoriaModel) {
        return productoRepository.findByCategoria(categoriaService.transform(categoriaModel));
    }

    public Producto findProducto(Long id) {
    	return productoRepository.findById(id)
    	        .orElseThrow(() -> new RuntimeException("No se encontró el producto con ID " + id));
    }
    
    public Producto updateProducto(ProductoModel productoModel) {
		return productoRepository.save(transform(productoModel));
	}
    
    public void removeProducto(Long id) {
        Producto producto = findProducto(id);
        productoRepository.delete(producto);
    }

	public Producto transform(ProductoModel productoModel) {
		ModelMapper modelMapper=new ModelMapper();
		return modelMapper.map(productoModel, Producto.class);
	}

	public ProductoModel transform(Producto producto) {
		ModelMapper modelMapper=new ModelMapper();
		return modelMapper.map(producto, ProductoModel.class);
	}
}
