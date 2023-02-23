package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Set;

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

    public CategoriaModel addCategoria(CategoriaModel categoriaModel) {
        return transform(categoriaRepository.save(transform(categoriaModel)));
    }

    public CategoriaModel findCategoria(Long id) {
        return transform(categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la categoria con ID " + id)));
    }

    public CategoriaModel updateCategoria(CategoriaModel categoriaModel) {
        return transform(categoriaRepository.save(transform(categoriaModel)));
    }

    public void removeCategoria(Long id) {
        categoriaRepository.delete(transform(findCategoria(id)));
    }

    @Override
    public void removeProductosByCategoria(long id) {
        Categoria categoria = transform(findCategoria(id));
        if (categoria != null) {
            List<Producto> productos = categoria.getProductos();
            if (productos != null && !productos.isEmpty()) {
                productoRepository.deleteAll(productos);
                categoria.setProductos(null);
                categoriaRepository.save(categoria);
            }
        }
    }

	public Categoria transform(CategoriaModel categoriaModel) {
		ModelMapper modelMapper=new ModelMapper();
		return modelMapper.map(categoriaModel, Categoria.class);
	}

	public CategoriaModel transform(Categoria categoria) {
		ModelMapper modelMapper=new ModelMapper();
		return modelMapper.map(categoria, CategoriaModel.class);
	}

	public List<CategoriaModel> listCategorias() {
		return categoriaRepository.findAll().stream().map(x->transform(x)).toList();
	}
}
