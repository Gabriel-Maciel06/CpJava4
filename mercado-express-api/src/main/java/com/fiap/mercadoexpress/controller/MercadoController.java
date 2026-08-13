package com.fiap.mercadoexpress.controller;

import com.fiap.mercadoexpress.assembler.MercadoModelAssembler;
import com.fiap.mercadoexpress.dto.MercadoPatchDto;
import com.fiap.mercadoexpress.dto.MercadoRequestDto;
import com.fiap.mercadoexpress.dto.MercadoResponseDto;
import com.fiap.mercadoexpress.service.MercadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/mercado")
@RequiredArgsConstructor
@Tag(name = "Mercado Express", description = "Endpoints para gerenciamento do estoque e produtos do Mercado Express (CRUD + HATEOAS)")
public class MercadoController {

    private final MercadoService service;

    @GetMapping
    @Operation(summary = "Listar todos os produtos", description = "Retorna a lista completa de produtos cadastrados no Mercado Express com suporte a HATEOAS e filtro opcional por tipo.")
    @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso")
    public ResponseEntity<CollectionModel<MercadoResponseDto>> findAll(
            @RequestParam(required = false) String tipo) {
        CollectionModel<MercadoResponseDto> response = service.findAll(tipo);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID", description = "Retorna os detalhes de um produto específico cadastrado no banco de dados Oracle.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<MercadoResponseDto> findById(@PathVariable Long id) {
        MercadoResponseDto response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo produto", description = "Insere um novo produto no banco de dados do Mercado Express.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos")
    })
    public ResponseEntity<MercadoResponseDto> create(@Valid @RequestBody MercadoRequestDto dto) {
        MercadoResponseDto response = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto por completo", description = "Atualiza todos os campos de um produto existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<MercadoResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody MercadoRequestDto dto) {
        MercadoResponseDto response = service.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar produto parcialmente", description = "Atualiza pontualmente um ou mais campos de um produto existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto atualizado parcialmente com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<MercadoResponseDto> patch(
            @PathVariable Long id,
            @Valid @RequestBody MercadoPatchDto dto) {
        MercadoResponseDto response = service.patch(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir produto", description = "Remove um produto do banco de dados pelo seu ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Produto removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
