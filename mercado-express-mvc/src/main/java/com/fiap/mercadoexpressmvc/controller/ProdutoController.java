package com.fiap.mercadoexpressmvc.controller;

import com.fiap.mercadoexpressmvc.dto.ProdutoDto;
import com.fiap.mercadoexpressmvc.model.CategoriaProduto;
import com.fiap.mercadoexpressmvc.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    /**
     * READ (Listagem com filtros por Nome e Categoria)
     */
    @GetMapping
    public String listar(
            @RequestParam(value = "busca", required = false) String busca,
            @RequestParam(value = "categoria", required = false) CategoriaProduto categoria,
            Model model) {

        model.addAttribute("produtos", service.listarTodos(busca, categoria));
        model.addAttribute("categorias", CategoriaProduto.values());
        model.addAttribute("buscaAtual", busca);
        model.addAttribute("categoriaAtual", categoria);
        return "produtos/lista";
    }

    /**
     * CREATE (Exibir formulário de cadastro)
     */
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("produtoDto", new ProdutoDto());
        model.addAttribute("categorias", CategoriaProduto.values());
        model.addAttribute("modoEdicao", false);
        return "produtos/form";
    }

    /**
     * UPDATE (Exibir formulário para editar produto existente)
     */
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<ProdutoDto> produtoOpt = service.buscarPorId(id);

        if (produtoOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Produto com ID " + id + " não encontrado.");
            return "redirect:/produtos";
        }

        model.addAttribute("produtoDto", produtoOpt.get());
        model.addAttribute("categorias", CategoriaProduto.values());
        model.addAttribute("modoEdicao", true);
        return "produtos/form";
    }

    /**
     * CREATE / UPDATE (Processamento do formulário com validação Bean Validation)
     */
    @PostMapping("/salvar")
    public String salvar(
            @Valid @ModelAttribute("produtoDto") ProdutoDto produtoDto,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("categorias", CategoriaProduto.values());
            model.addAttribute("modoEdicao", produtoDto.getId() != null);
            return "produtos/form";
        }

        boolean isEdicao = produtoDto.getId() != null;
        service.salvar(produtoDto);

        if (isEdicao) {
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Produto '" + produtoDto.getNome() + "' atualizado com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Produto '" + produtoDto.getNome() + "' cadastrado com sucesso!");
        }

        return "redirect:/produtos";
    }

    /**
     * READ (Detalhes de um produto específico)
     */
    @GetMapping("/detalhes/{id}")
    public String detalhes(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<ProdutoDto> produtoOpt = service.buscarPorId(id);

        if (produtoOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Produto com ID " + id + " não encontrado.");
            return "redirect:/produtos";
        }

        model.addAttribute("produto", produtoOpt.get());
        return "produtos/detalhes";
    }

    /**
     * DELETE (Excluir produto por ID)
     */
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            service.excluir(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Produto removido com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao excluir o produto: " + e.getMessage());
        }
        return "redirect:/produtos";
    }
}
