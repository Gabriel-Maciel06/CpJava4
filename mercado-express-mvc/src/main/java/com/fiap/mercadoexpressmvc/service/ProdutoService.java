package com.fiap.mercadoexpressmvc.service;

import com.fiap.mercadoexpressmvc.dto.ProdutoDto;
import com.fiap.mercadoexpressmvc.model.CategoriaProduto;
import com.fiap.mercadoexpressmvc.model.Produto;
import com.fiap.mercadoexpressmvc.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ProdutoDto> listarTodos(String busca, CategoriaProduto categoria) {
        List<Produto> produtos;

        if (busca != null && !busca.isBlank() && categoria != null) {
            produtos = repository.findByNomeContainingIgnoreCaseAndCategoria(busca.trim(), categoria);
        } else if (busca != null && !busca.isBlank()) {
            produtos = repository.findByNomeContainingIgnoreCase(busca.trim());
        } else if (categoria != null) {
            produtos = repository.findByCategoria(categoria);
        } else {
            produtos = repository.findAll();
        }

        return produtos.stream().map(ProdutoDto::fromEntity).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ProdutoDto> buscarPorId(Long id) {
        return repository.findById(id).map(ProdutoDto::fromEntity);
    }

    @Transactional
    public ProdutoDto salvar(ProdutoDto dto) {
        Produto entity = dto.toEntity();
        Produto salvo = repository.save(entity);
        return ProdutoDto.fromEntity(salvo);
    }

    @Transactional
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Produto não encontrado para o ID: " + id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public long contarTotalProdutos() {
        return repository.count();
    }

    @Transactional(readOnly = true)
    public long contarEstoqueBaixo() {
        return repository.countEstoqueBaixo();
    }

    @Transactional(readOnly = true)
    public BigDecimal calcularValorTotalEstoque() {
        return repository.sumValorTotalEstoque();
    }
}
