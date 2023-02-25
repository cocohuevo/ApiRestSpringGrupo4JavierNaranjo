package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

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

import com.example.demo.model.CategoriaModel;
import com.example.demo.model.ProductoModel;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.ProductoService;
import com.example.demo.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("/api")
public class ProductoController {

    @Autowired
    @Qualifier("productoService")
    private ProductoService productoService;
    
    @Autowired
    @Qualifier("userService")
    private UserServiceImpl userService;
    
    @Autowired
    @Qualifier("categoriaService")
    private CategoriaService categoriaService;
    
    @PostMapping("/categories/{categoriaId}/product")
    public ProductoModel crearProducto(@PathVariable(value = "categoriaId") long categoriaId, @RequestBody ProductoModel productoModel) {
        productoModel.setCategoriaId(categoriaId);
        return productoService.addProducto(productoModel);
    }
    @GetMapping("/categories/{categoriaId}/products")
    public List<ProductoModel> obtenerProductosPorCategoria(@PathVariable(value = "categoriaId") Long categoriaId) {
        CategoriaModel categoriaModel = categoriaService.findCategoria(categoriaId);
        return productoService.findProductosByCategoria(categoriaModel);
    }
    @GetMapping("/products/{id}")
    public ProductoModel obtenerProductoPorId(@PathVariable(value = "id") Long id) {
        return productoService.findProducto(id);
    }
    @GetMapping("/products")
    public List<ProductoModel> obtenerProductos() {
        return productoService.listProductos();
    }
    @PutMapping("/products")
    public ProductoModel actualizarProducto(@RequestBody ProductoModel productoActualizado) {
        return productoService.updateProducto(productoActualizado);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable(value = "id") Long id) {
        productoService.removeProducto(id);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/users/{userId}/favoritos/{productId}")
    public ResponseEntity<String> agregarFavorito(@PathVariable Long userId, @PathVariable Long productId) {
        try {
            boolean productoAgregado = userService.agregarFavorito(userId, productId);
            if (productoAgregado) {
                return ResponseEntity.ok("El producto con id " + productId + " ha sido agregado a la lista de favoritos del usuario con id " + userId + ".");
            } else {
                return ResponseEntity.badRequest().body("El producto con id " + productId + " ya se encuentra en la lista de favoritos del usuario con id " + userId + ".");
            }
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
