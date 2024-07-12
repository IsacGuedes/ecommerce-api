package br.com.guedes.ecommerce.service;

import br.com.guedes.ecommerce.domains.Produto;
import br.com.guedes.ecommerce.dto.ProdutoRequest;
import br.com.guedes.ecommerce.dto.ProdutoResponse;
import br.com.guedes.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    public ProdutoResponse criarProduto(ProdutoRequest request){
        Produto produto = new Produto();
        produto.setNome(request.getNome());
        produto.setPreco(request.getPreco());
        produto.setDescricao(request.getDescricao());
        produto.setImagemUrl(request.getImagemUrl());

        Produto salvo = produtoRepository.save(produto);
        return new ProdutoResponse(salvo);
    }

    private void validarProdutoRequest(ProdutoRequest request) {
        if (request.getNome() == null || request.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }
        if (request.getPreco() == null || request.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser positivo.");
        }
        if (request.getDescricao() == null || request.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição do produto é obrigatória.");
        }
        if (request.getImagemUrl() != null && !request.getImagemUrl().trim().isEmpty()) {
            // Aqui você pode adicionar uma validação de URL, se necessário
            if (!request.getImagemUrl().matches("^(http[s]?://.*\\.(?:png|jpg|gif|svg|jpeg))$")) {
                throw new IllegalArgumentException("A URL da imagem do produto é inválida.");
            }
        }
    }

    public List<ProdutoResponse> listarProdutos(){
        return produtoRepository.findAll().stream()
                .map(ProdutoResponse::new)
                .collect(Collectors.toList());
    }

    public ProdutoResponse atualizarProduto(Long id, ProdutoRequest request){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        Produto atualizado = produtoRepository.save(produto);
        return new ProdutoResponse(atualizado);
    }

    public ProdutoResponse obterProdutoPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return new ProdutoResponse(produto);
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
