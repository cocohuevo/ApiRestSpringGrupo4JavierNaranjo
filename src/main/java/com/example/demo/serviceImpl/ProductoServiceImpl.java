package com.example.demo.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Producto;
import com.example.demo.model.CategoriaModel;
import com.example.demo.model.ProductoModel;
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

    public ProductoModel addProducto(ProductoModel productoModel) {
        return transform(productoRepository.save(transform(productoModel)));
    }

    public List<ProductoModel> findProductosByCategoria(CategoriaModel categoriaModel) {
        return productoRepository.findByCategoria(categoriaService.transform(categoriaModel)).stream().map(x->transform(x)).toList();
    }

    public ProductoModel findProducto(Long id) {
    	return transform(productoRepository.findById(id)
    	        .orElseThrow(() -> new RuntimeException("No se encontró el producto con ID " + id)));
    }
    
    public ProductoModel updateProducto(ProductoModel productoModel) {
		return transform(productoRepository.save(transform(productoModel)));
	}
    
    public void removeProducto(Long id) {
        productoRepository.delete(transform(findProducto(id)));
    }

	public Producto transform(ProductoModel productoModel) {
		ModelMapper modelMapper=new ModelMapper();
		return modelMapper.map(productoModel, Producto.class);
	}

	public ProductoModel transform(Producto producto) {
		ModelMapper modelMapper=new ModelMapper();
		return modelMapper.map(producto, ProductoModel.class);
	}

	public List<ProductoModel> listProductos() {
		return productoRepository.findAll().stream().map(x->transform(x)).toList();
	}
}
