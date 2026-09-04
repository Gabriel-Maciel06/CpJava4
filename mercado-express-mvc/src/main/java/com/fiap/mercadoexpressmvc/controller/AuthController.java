package com.fiap.mercadoexpressmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {

        if (error != null) {
            model.addAttribute("mensagemErro", "Usuário ou senha inválidos. Verifique suas credenciais.");
        }

        if (logout != null) {
            model.addAttribute("mensagemSucesso", "Você foi desconectado com sucesso.");
        }

        return "login";
    }

    @GetMapping("/acesso-negado")
    public String acessoNegado(Model model) {
        model.addAttribute("titulo", "Acesso Negado (403)");
        model.addAttribute("mensagem", "Você não possui permissão para executar esta operação. Apenas administradores (ROLE_ADMIN) podem excluir produtos.");
        return "acesso-negado";
    }
}
