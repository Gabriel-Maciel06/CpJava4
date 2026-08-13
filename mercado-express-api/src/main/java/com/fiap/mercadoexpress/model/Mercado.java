package com.fiap.mercadoexpress.model;

import jakarta.persistence.*;
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

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Column(name = "TIPO", nullable = false, length = 50)
    private String tipo;

    @Column(name = "SETOR", nullable = false, length = 50)
    private String setor;

    @Column(name = "TAMANHO", nullable = false, length = 30)
    private String tamanho;

    @Column(name = "PRECO", nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;
}
