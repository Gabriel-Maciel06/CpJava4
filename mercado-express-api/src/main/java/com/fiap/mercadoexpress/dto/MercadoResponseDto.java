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
}
