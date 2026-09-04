package com.fiap.mercadoexpressmvc.model;

public enum CategoriaProduto {
    HIGIENE_LIMPEZA("Higiene e Limpeza", "badge-limpeza"),
    HORTIFRUTI("Hortifruti / Alimentos", "badge-hortifruti"),
    VESTUARIO("Vestuário & Moda", "badge-vestuario"),
    BRINQUEDOS("Brinquedos & Lazer", "badge-brinquedos"),
    BEBIDAS("Bebidas", "badge-bebidas"),
    PADARIA("Padaria & Confeitaria", "badge-padaria");

    private final String descricao;
    private final String badgeClass;

    CategoriaProduto(String descricao, String badgeClass) {
        this.descricao = descricao;
        this.badgeClass = badgeClass;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getBadgeClass() {
        return badgeClass;
    }
}
