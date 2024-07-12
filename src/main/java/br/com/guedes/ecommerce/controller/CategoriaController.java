package br.com.guedes.ecommerce.controller;

import br.com.guedes.ecommerce.dto.CategoriaRequest;
import br.com.guedes.ecommerce.dto.CategoriaResponse;
import br.com.guedes.ecommerce.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;


    @PostMapping
    public ResponseEntity<CategoriaResponse> criarCategoria(@RequestBody CategoriaRequest request) {
        CategoriaResponse response = categoriaService.criarCategoria(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listarCategorias() {
        List<CategoriaResponse> response = categoriaService.listarCategorias();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> obterCategoriaPorId(@PathVariable Long id) {
        CategoriaResponse response = categoriaService.obterCategoriaPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaRequest request) {
        CategoriaResponse response = categoriaService.atualizarCategoria(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.ok().build();
    }
}

