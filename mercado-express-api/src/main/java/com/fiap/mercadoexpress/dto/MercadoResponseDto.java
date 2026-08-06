package com.fiap.mercadoexpress.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "produtos", itemRelation = "produto")
public class MercadoResponseDto extends RepresentationModel<MercadoResponseDto> {

    private Long id;
    private String nome;
    private String tipo;
    private String setor;
    private String tamanho;
    private BigDecimal preco;

    public MercadoResponseDto(Long id, String nome, String tipo, String setor, String tamanho, BigDecimal preco) {
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

    public static MercadoResponseDtoBuilder builder() { return new MercadoResponseDtoBuilder(); }

    public static class MercadoResponseDtoBuilder {
        private Long id;
        private String nome;
        private String tipo;
        private String setor;
        private String tamanho;
        private BigDecimal preco;

        public MercadoResponseDtoBuilder id(Long id) { this.id = id; return this; }
        public MercadoResponseDtoBuilder nome(String nome) { this.nome = nome; return this; }
        public MercadoResponseDtoBuilder tipo(String tipo) { this.tipo = tipo; return this; }
        public MercadoResponseDtoBuilder setor(String setor) { this.setor = setor; return this; }
        public MercadoResponseDtoBuilder tamanho(String tamanho) { this.tamanho = tamanho; return this; }
        public MercadoResponseDtoBuilder preco(BigDecimal preco) { this.preco = preco; return this; }

        public MercadoResponseDto build() {
            return new MercadoResponseDto(id, nome, tipo, setor, tamanho, preco);
        }
    }
}
