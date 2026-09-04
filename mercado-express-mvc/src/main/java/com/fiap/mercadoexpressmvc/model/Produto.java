package com.fiap.mercadoexpressmvc.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TDS_MVC_TB_MERCADO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME", nullable = false, length = 120)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORIA", nullable = false, length = 30)
    private CategoriaProduto categoria;

    @Column(name = "CODIGO_BARRAS", length = 30)
    private String codigoBarras;

    @Column(name = "PRECO", nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "ESTOQUE", nullable = false)
    private Integer estoque;

    @Column(name = "DESCRICAO", length = 300)
    private String descricao;

    @Column(name = "DATA_CADASTRO", nullable = false)
    private LocalDateTime dataCadastro;

    public Produto() {}

    public Produto(Long id, String nome, CategoriaProduto categoria, String codigoBarras, BigDecimal preco, Integer estoque, String descricao, LocalDateTime dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.codigoBarras = codigoBarras;
        this.preco = preco;
        this.estoque = estoque;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
    }

    @PrePersist
    public void prePersist() {
        if (this.dataCadastro == null) {
            this.dataCadastro = LocalDateTime.now();
        }
    }

    public static ProdutoBuilder builder() {
        return new ProdutoBuilder();
    }

    public static class ProdutoBuilder {
        private Long id;
        private String nome;
        private CategoriaProduto categoria;
        private String codigoBarras;
        private BigDecimal preco;
        private Integer estoque;
        private String descricao;
        private LocalDateTime dataCadastro;

        public ProdutoBuilder id(Long id) { this.id = id; return this; }
        public ProdutoBuilder nome(String nome) { this.nome = nome; return this; }
        public ProdutoBuilder categoria(CategoriaProduto categoria) { this.categoria = categoria; return this; }
        public ProdutoBuilder codigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; return this; }
        public ProdutoBuilder preco(BigDecimal preco) { this.preco = preco; return this; }
        public ProdutoBuilder estoque(Integer estoque) { this.estoque = estoque; return this; }
        public ProdutoBuilder descricao(String descricao) { this.descricao = descricao; return this; }
        public ProdutoBuilder dataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; return this; }

        public Produto build() {
            return new Produto(id, nome, categoria, codigoBarras, preco, estoque, descricao, dataCadastro);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public CategoriaProduto getCategoria() { return categoria; }
    public void setCategoria(CategoriaProduto categoria) { this.categoria = categoria; }
    public String getCodigoBarras() { return codigoBarras; }
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public Integer getEstoque() { return estoque; }
    public void setEstoque(Integer estoque) { this.estoque = estoque; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
}
