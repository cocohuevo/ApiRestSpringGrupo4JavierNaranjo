package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.Producto;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.ProductoService;

@RestController
@RequestMapping("/api")
public class ProductoController {

    @Autowired
    @Qualifier("productoService")
    private ProductoService productoService;
    
    @Autowired
    @Qualifier("categoriaService")
    private CategoriaService categoriaService;
    
    @PostMapping("/categories/{categoriaId}/product")
    public Producto crearProducto(@PathVariable(value = "categoriaId") Long categoriaId, @RequestBody Producto producto) {
        Categoria categoria = categoriaService.findCategoria(categoriaId);
        producto.setCategoria(categoria);
        return productoService.addProducto(producto);
    }

    @GetMapping("/categories/{categoriaId}/products")
    public List<Producto> obtenerProductosPorCategoria(@PathVariable(value = "categoriaId") Long categoriaId) {
        Categoria categoria = categoriaService.findCategoria(categoriaId);
        return productoService.findProductosByCategoria(categoria);
    }

    @GetMapping("/products/{id}")
    public Producto obtenerProductoPorId(@PathVariable(value = "id") Long id) {
        return productoService.findProducto(id);
    }

    @PutMapping("/products/{id}")
    public Producto actualizarProducto(@PathVariable(value = "id") Long id, @RequestBody Producto productoActualizado) {
        return productoService.updateProducto(id, productoActualizado);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable(value = "id") Long id) {
        productoService.removeProducto(id);
        return ResponseEntity.ok().build();
    }
}
