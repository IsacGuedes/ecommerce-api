package br.com.guedes.ecommerce.service;

import br.com.guedes.ecommerce.domains.Categoria;
import br.com.guedes.ecommerce.dto.CategoriaResponse;
import br.com.guedes.ecommerce.dto.CategoriaRequest;
import br.com.guedes.ecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    public CategoriaResponse criarCategoria(CategoriaRequest request){
        Categoria categoria = new Categoria();

        categoria.setNome(request.getNome());
        Categoria salva = categoriaRepository.save(categoria);
        return new CategoriaResponse(salva);
    }
    public List<CategoriaResponse> listarCategorias(){
        return categoriaRepository.findAll().stream()
                .map(CategoriaResponse::new)
                .collect(Collectors.toList());
    }
    public CategoriaResponse atualizarCategoria(Long id, CategoriaRequest request){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        categoria.setNome(request.getNome());
        Categoria salva = categoriaRepository.save(categoria);
        return new CategoriaResponse(salva);
    }
    public CategoriaResponse obterCategoriaPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return new CategoriaResponse(categoria);
    }
    public void deletarCategoria(Long id){
        categoriaRepository.deleteById(id);
    }
}
