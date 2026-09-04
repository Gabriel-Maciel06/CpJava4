package com.fiap.mercadoexpressmvc.repository;

import com.fiap.mercadoexpressmvc.model.CategoriaProduto;
import com.fiap.mercadoexpressmvc.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByNomeContainingIgnoreCase(String nome);

    List<Produto> findByCategoria(CategoriaProduto categoria);

    List<Produto> findByNomeContainingIgnoreCaseAndCategoria(String nome, CategoriaProduto categoria);

    @Query("SELECT COUNT(p) FROM Produto p WHERE p.estoque <= 5")
    long countEstoqueBaixo();

    @Query("SELECT COALESCE(SUM(p.preco * p.estoque), 0) FROM Produto p")
    BigDecimal sumValorTotalEstoque();
}
