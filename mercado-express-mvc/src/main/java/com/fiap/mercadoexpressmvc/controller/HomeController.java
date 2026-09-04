package com.fiap.mercadoexpressmvc.controller;

import com.fiap.mercadoexpressmvc.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProdutoService produtoService;

    public HomeController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("totalProdutos", produtoService.contarTotalProdutos());
        model.addAttribute("estoqueBaixo", produtoService.contarEstoqueBaixo());
        model.addAttribute("valorTotalEstoque", produtoService.calcularValorTotalEstoque());
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return index(model);
    }
}
