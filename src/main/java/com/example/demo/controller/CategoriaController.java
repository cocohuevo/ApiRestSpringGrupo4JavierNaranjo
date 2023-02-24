package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Categoria;
import com.example.demo.model.CategoriaModel;
import com.example.demo.service.CategoriaService;

@RestController
@RequestMapping("/api")
public class CategoriaController {

    @Autowired
    @Qualifier("categoriaService")
    private CategoriaService categoriaService;
    @PostMapping("/categories")
    public CategoriaModel crearCategoria(@RequestBody CategoriaModel categoriaModel) {
        return categoriaService.addCategoria(categoriaModel);
    }
    @GetMapping("/categories/{id}")
    public CategoriaModel obtenerCategoriaPorId(@PathVariable(value = "id") Long id) {
        return categoriaService.findCategoria(id);
    }
    @GetMapping("/categories")
    public List<CategoriaModel> obtenerCategorias() {
        return categoriaService.listCategorias();
    }
    @PutMapping("/categories")
    public CategoriaModel actualizarCategoria(@RequestBody CategoriaModel categoriaModel) {
        return categoriaService.updateCategoria(categoriaModel);
    }
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable long id) {
        Categoria categoria = categoriaService.transform(categoriaService.findCategoria(id));
        if (categoria != null) {
            categoriaService.removeProductosByCategoria(id);
            categoriaService.removeCategoria(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    //@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @DeleteMapping("/categories/{id}/products")
    public ResponseEntity<?> eliminarProductosPorCategoria(@PathVariable(value = "id") Long id) {
        categoriaService.removeProductosByCategoria(id);
        return ResponseEntity.ok().build();
    }
}
