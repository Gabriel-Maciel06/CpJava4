package com.fiap.mercadoexpress.dto;

import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MercadoPatchDto {

    private String nome;
    private String tipo;
    private String setor;
    private String tamanho;

    @Positive(message = "Se informado, o preço deve ser maior que zero.")
    private BigDecimal preco;
}
