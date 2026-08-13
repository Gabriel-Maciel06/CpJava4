package com.fiap.mercadoexpress.assembler;

import com.fiap.mercadoexpress.controller.MercadoController;
import com.fiap.mercadoexpress.dto.MercadoResponseDto;
import com.fiap.mercadoexpress.model.Mercado;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Assembler responsável por converter a entidade Mercado em MercadoResponseDto
 * e adicionar os links HATEOAS (Nível 3 de Maturidade de Richardson).
 * Mantém a lógica de hipermídia separada da camada de serviço.
 */
@Component
public class MercadoModelAssembler extends RepresentationModelAssemblerSupport<Mercado, MercadoResponseDto> {

    public MercadoModelAssembler() {
        super(MercadoController.class, MercadoResponseDto.class);
    }

    @Override
    public MercadoResponseDto toModel(Mercado mercado) {
        MercadoResponseDto dto = MercadoResponseDto.builder()
                .id(mercado.getId())
                .nome(mercado.getNome())
                .tipo(mercado.getTipo())
                .setor(mercado.getSetor())
                .tamanho(mercado.getTamanho())
                .preco(mercado.getPreco())
                .build();

        // Links HATEOAS — Maturidade Nível 3 de Richardson
        dto.add(linkTo(methodOn(MercadoController.class).findById(mercado.getId())).withSelfRel());
        dto.add(linkTo(methodOn(MercadoController.class).findAll(null)).withRel("todos-produtos"));
        dto.add(linkTo(methodOn(MercadoController.class).update(mercado.getId(), null)).withRel("atualizar"));
        dto.add(linkTo(methodOn(MercadoController.class).patch(mercado.getId(), null)).withRel("atualizar-parcial"));
        dto.add(linkTo(methodOn(MercadoController.class).delete(mercado.getId())).withRel("deletar"));

        return dto;
    }
}
