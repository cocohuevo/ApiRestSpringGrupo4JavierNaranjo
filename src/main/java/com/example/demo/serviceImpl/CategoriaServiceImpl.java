package com.example.demo.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.Producto;
import com.example.demo.model.CategoriaModel;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.CategoriaService;

@Service("categoriaService")
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    @Qualifier("categoriaRepository")
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    @Qualifier("productoRepository")
    private ProductoRepository productoRepository;

    public Categoria addCategoria(CategoriaModel categoriaModel) {
        return categoriaRepository.save(transform(categoriaModel));
    }

    public Categoria findCategoria(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la categoria con ID " + id));
    }

    public Categoria updateCategoria(CategoriaModel categoriaModel) {
        return categoriaRepository.save(transform(categoriaModel));
    }

    public void removeCategoria(Long id) {
        Categoria categoria = findCategoria(id);
        categoriaRepository.delete(categoria);
    }

    public void removeProductosByCategoria(CategoriaModel categoriaModel) {
        List<Producto> productos = transform(categoriaModel).getProductos();
        transform(categoriaModel).setProductos(null);
        for (Producto producto : productos) {
            producto.setCategoria(null);
            productoRepository.delete(producto);
        }
        categoriaRepository.delete(transform(categoriaModel));
    }

	public Categoria transform(CategoriaModel categoriaModel) {
		ModelMapper modelMapper=new ModelMapper();
		return modelMapper.map(categoriaModel, Categoria.class);
	}

	public CategoriaModel transform(Categoria categoria) {
		ModelMapper modelMapper=new ModelMapper();
		return modelMapper.map(categoria, CategoriaModel.class);
	}
}
