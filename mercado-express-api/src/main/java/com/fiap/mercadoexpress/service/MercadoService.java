package com.fiap.mercadoexpress.service;

import com.fiap.mercadoexpress.controller.MercadoController;
import com.fiap.mercadoexpress.dto.MercadoPatchDto;
import com.fiap.mercadoexpress.dto.MercadoRequestDto;
import com.fiap.mercadoexpress.dto.MercadoResponseDto;
import com.fiap.mercadoexpress.exception.ResourceNotFoundException;
import com.fiap.mercadoexpress.model.Mercado;
import com.fiap.mercadoexpress.repository.MercadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class MercadoService {

    private final MercadoRepository repository;

    public MercadoService(MercadoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public CollectionModel<MercadoResponseDto> findAll(String tipo) {
        List<Mercado> lista = (tipo != null && !tipo.isBlank())
                ? repository.findByTipoIgnoreCase(tipo)
                : repository.findAll();

        List<MercadoResponseDto> dtos = lista.stream()
                .map(this::convertToDto)
                .toList();

        Link selfLink = linkTo(methodOn(MercadoController.class).findAll(tipo)).withSelfRel();
        return CollectionModel.of(dtos, selfLink);
    }

    @Transactional(readOnly = true)
    public MercadoResponseDto findById(Long id) {
        Mercado mercado = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o ID: " + id));
        return convertToDto(mercado);
    }

    @Transactional
    public MercadoResponseDto create(MercadoRequestDto dto) {
        Mercado mercado = Mercado.builder()
                .nome(dto.getNome())
                .tipo(dto.getTipo())
                .setor(dto.getSetor())
                .tamanho(dto.getTamanho())
                .preco(dto.getPreco())
                .build();

        Mercado salvo = repository.save(mercado);
        return convertToDto(salvo);
    }

    @Transactional
    public MercadoResponseDto update(Long id, MercadoRequestDto dto) {
        Mercado mercado = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado para atualização com ID: " + id));

        mercado.setNome(dto.getNome());
        mercado.setTipo(dto.getTipo());
        mercado.setSetor(dto.getSetor());
        mercado.setTamanho(dto.getTamanho());
        mercado.setPreco(dto.getPreco());

        Mercado atualizado = repository.save(mercado);
        return convertToDto(atualizado);
    }

    @Transactional
    public MercadoResponseDto patch(Long id, MercadoPatchDto dto) {
        Mercado mercado = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado para atualização parcial com ID: " + id));

        if (dto.getNome() != null && !dto.getNome().isBlank()) {
            mercado.setNome(dto.getNome());
        }
        if (dto.getTipo() != null && !dto.getTipo().isBlank()) {
            mercado.setTipo(dto.getTipo());
        }
        if (dto.getSetor() != null && !dto.getSetor().isBlank()) {
            mercado.setSetor(dto.getSetor());
        }
        if (dto.getTamanho() != null && !dto.getTamanho().isBlank()) {
            mercado.setTamanho(dto.getTamanho());
        }
        if (dto.getPreco() != null) {
            mercado.setPreco(dto.getPreco());
        }

        Mercado atualizado = repository.save(mercado);
        return convertToDto(atualizado);
    }

    @Transactional
    public void delete(Long id) {
        Mercado mercado = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado para exclusão com ID: " + id));
        repository.delete(mercado);
    }

    private MercadoResponseDto convertToDto(Mercado mercado) {
        MercadoResponseDto dto = MercadoResponseDto.builder()
                .id(mercado.getId())
                .nome(mercado.getNome())
                .tipo(mercado.getTipo())
                .setor(mercado.getSetor())
                .tamanho(mercado.getTamanho())
                .preco(mercado.getPreco())
                .build();

        // Adiciona links HATEOAS (Maturidade Nível 3 de Richardson)
        dto.add(linkTo(methodOn(MercadoController.class).findById(mercado.getId())).withSelfRel());
        dto.add(linkTo(methodOn(MercadoController.class).findAll(null)).withRel("todos-produtos"));
        dto.add(linkTo(methodOn(MercadoController.class).update(mercado.getId(), null)).withRel("atualizar"));
        dto.add(linkTo(methodOn(MercadoController.class).patch(mercado.getId(), null)).withRel("atualizar-parcial"));
        dto.add(linkTo(methodOn(MercadoController.class).delete(mercado.getId())).withRel("deletar"));

        return dto;
    }
}
