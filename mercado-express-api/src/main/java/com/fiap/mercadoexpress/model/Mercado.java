package com.fiap.mercadoexpress.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "TDS_TB_MERCADO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mercado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TDS_MERCADO")
    @SequenceGenerator(name = "SEQ_TDS_MERCADO", sequenceName = "SQ_TDS_MERCADO", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "O nome do produto é obrigatório.")
    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "O tipo do produto é obrigatório.")
    @Column(name = "TIPO", nullable = false, length = 50)
    private String tipo;

    @NotBlank(message = "O setor é obrigatório.")
    @Column(name = "SETOR", nullable = false, length = 50)
    private String setor;

    @NotBlank(message = "O tamanho é obrigatório.")
    @Column(name = "TAMANHO", nullable = false, length = 30)
    private String tamanho;

    @NotNull(message = "O preço é obrigatório.")
    @Positive(message = "O preço deve ser maior que zero.")
    @Column(name = "PRECO", nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    public Mercado() {}

    public Mercado(Long id, String nome, String tipo, String setor, String tamanho, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.setor = setor;
        this.tamanho = tamanho;
        this.preco = preco;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }

    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public static MercadoBuilder builder() { return new MercadoBuilder(); }

    public static class MercadoBuilder {
        private Long id;
        private String nome;
        private String tipo;
        private String setor;
        private String tamanho;
        private BigDecimal preco;

        public MercadoBuilder id(Long id) { this.id = id; return this; }
        public MercadoBuilder nome(String nome) { this.nome = nome; return this; }
        public MercadoBuilder tipo(String tipo) { this.tipo = tipo; return this; }
        public MercadoBuilder setor(String setor) { this.setor = setor; return this; }
        public MercadoBuilder tamanho(String tamanho) { this.tamanho = tamanho; return this; }
        public MercadoBuilder preco(BigDecimal preco) { this.preco = preco; return this; }

        public Mercado build() {
            return new Mercado(id, nome, tipo, setor, tamanho, preco);
        }
    }
}
