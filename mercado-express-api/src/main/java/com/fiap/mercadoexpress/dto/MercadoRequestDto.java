package com.fiap.mercadoexpress.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MercadoRequestDto {

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O tipo é obrigatório (ex: Alimentos, Limpeza, Brinquedo, Vestuário).")
    private String tipo;

    @NotBlank(message = "O setor é obrigatório (ex: Mercearia, Higiene, Infantil, Bazar).")
    private String setor;

    @NotBlank(message = "O tamanho é obrigatório (ex: 500g, 1L, M, Único, 30cm).")
    private String tamanho;

    @NotNull(message = "O preço é obrigatório.")
    @Positive(message = "O preço deve ser um valor positivo.")
    private BigDecimal preco;
}
