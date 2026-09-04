package com.fiap.mercadoexpressmvc.dto;

import com.fiap.mercadoexpressmvc.model.CategoriaProduto;
import com.fiap.mercadoexpressmvc.model.Produto;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProdutoDto {

    private Long id;

    @NotBlank(message = "O nome do produto é obrigatório")
    @Size(min = 2, max = 120, message = "O nome deve conter entre 2 e 120 caracteres")
    private String nome;

    @NotNull(message = "A categoria do produto é obrigatória")
    private CategoriaProduto categoria;

    @Pattern(regexp = "^[0-9]{8,14}$", message = "O código de barras deve conter entre 8 e 14 dígitos numéricos (ex: EAN-13)")
    private String codigoBarras;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser maior que zero (R$ 0,00)")
    private BigDecimal preco;

    @NotNull(message = "A quantidade em estoque é obrigatória")
    @Min(value = 0, message = "A quantidade em estoque não pode ser negativa")
    private Integer estoque;

    @Size(max = 300, message = "A descrição não pode ultrapassar 300 caracteres")
    private String descricao;

    private LocalDateTime dataCadastro;

    public ProdutoDto() {}

    public ProdutoDto(Long id, String nome, CategoriaProduto categoria, String codigoBarras, BigDecimal preco, Integer estoque, String descricao, LocalDateTime dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.codigoBarras = codigoBarras;
        this.preco = preco;
        this.estoque = estoque;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
    }

    public static ProdutoDtoBuilder builder() {
        return new ProdutoDtoBuilder();
    }

    public static class ProdutoDtoBuilder {
        private Long id;
        private String nome;
        private CategoriaProduto categoria;
        private String codigoBarras;
        private BigDecimal preco;
        private Integer estoque;
        private String descricao;
        private LocalDateTime dataCadastro;

        public ProdutoDtoBuilder id(Long id) { this.id = id; return this; }
        public ProdutoDtoBuilder nome(String nome) { this.nome = nome; return this; }
        public ProdutoDtoBuilder categoria(CategoriaProduto categoria) { this.categoria = categoria; return this; }
        public ProdutoDtoBuilder codigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; return this; }
        public ProdutoDtoBuilder preco(BigDecimal preco) { this.preco = preco; return this; }
        public ProdutoDtoBuilder estoque(Integer estoque) { this.estoque = estoque; return this; }
        public ProdutoDtoBuilder descricao(String descricao) { this.descricao = descricao; return this; }
        public ProdutoDtoBuilder dataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; return this; }

        public ProdutoDto build() {
            return new ProdutoDto(id, nome, categoria, codigoBarras, preco, estoque, descricao, dataCadastro);
        }
    }

    public static ProdutoDto fromEntity(Produto produto) {
        return ProdutoDto.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .categoria(produto.getCategoria())
                .codigoBarras(produto.getCodigoBarras())
                .preco(produto.getPreco())
                .estoque(produto.getEstoque())
                .descricao(produto.getDescricao())
                .dataCadastro(produto.getDataCadastro())
                .build();
    }

    public Produto toEntity() {
        return Produto.builder()
                .id(this.id)
                .nome(this.nome)
                .categoria(this.categoria)
                .codigoBarras(this.codigoBarras)
                .preco(this.preco)
                .estoque(this.estoque)
                .descricao(this.descricao)
                .dataCadastro(this.dataCadastro)
                .build();
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
