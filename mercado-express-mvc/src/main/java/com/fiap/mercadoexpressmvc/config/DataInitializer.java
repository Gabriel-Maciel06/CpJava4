package com.fiap.mercadoexpressmvc.config;

import com.fiap.mercadoexpressmvc.model.CategoriaProduto;
import com.fiap.mercadoexpressmvc.model.Produto;
import com.fiap.mercadoexpressmvc.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataInitializer implements CommandLineRunner {

    private final ProdutoRepository repository;

    public DataInitializer(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            List<Produto> produtosIniciais = List.of(
                Produto.builder()
                    .nome("Sabão Líquido Omo Lavagem Perfeita 3L")
                    .categoria(CategoriaProduto.HIGIENE_LIMPEZA)
                    .codigoBarras("7891038000111")
                    .preco(new BigDecimal("39.90"))
                    .estoque(25)
                    .descricao("Detergente líquido concentrado para roupas brancas e coloridas.")
                    .dataCadastro(LocalDateTime.now().minusDays(10))
                    .build(),

                Produto.builder()
                    .nome("Amaciante Comfort Concentrado 1.5L")
                    .categoria(CategoriaProduto.HIGIENE_LIMPEZA)
                    .codigoBarras("7891038000222")
                    .preco(new BigDecimal("22.50"))
                    .estoque(4) // Estoque baixo (< 5)
                    .descricao("Amaciante concentrado com cápsulas de perfume de longa duração.")
                    .dataCadastro(LocalDateTime.now().minusDays(8))
                    .build(),

                Produto.builder()
                    .nome("Maçã Fuji Nacional Selecionada (kg)")
                    .categoria(CategoriaProduto.HORTIFRUTI)
                    .codigoBarras("7892000000333")
                    .preco(new BigDecimal("9.98"))
                    .estoque(80)
                    .descricao("Maçãs frescas e crocantes direto do produtor.")
                    .dataCadastro(LocalDateTime.now().minusDays(6))
                    .build(),

                Produto.builder()
                    .nome("Banana Prata Climatizada (kg)")
                    .categoria(CategoriaProduto.HORTIFRUTI)
                    .codigoBarras("7892000000444")
                    .preco(new BigDecimal("6.49"))
                    .estoque(3) // Estoque baixo
                    .descricao("Banana prata doce selecionada de primeira qualidade.")
                    .dataCadastro(LocalDateTime.now().minusDays(5))
                    .build(),

                Produto.builder()
                    .nome("Kit 3 Pares de Meia Algodão Lupo")
                    .categoria(CategoriaProduto.VESTUARIO)
                    .codigoBarras("7893000000555")
                    .preco(new BigDecimal("29.90"))
                    .estoque(45)
                    .descricao("Meias cano médio confeccionadas em algodão macio com elastano.")
                    .dataCadastro(LocalDateTime.now().minusDays(4))
                    .build(),

                Produto.builder()
                    .nome("Jogo de Tabuleiro Banco Imobiliário")
                    .categoria(CategoriaProduto.BRINQUEDOS)
                    .codigoBarras("7894000000666")
                    .preco(new BigDecimal("119.90"))
                    .estoque(12)
                    .descricao("Clássico jogo de compra e venda de propriedades para toda a família.")
                    .dataCadastro(LocalDateTime.now().minusDays(3))
                    .build(),

                Produto.builder()
                    .nome("Suco de Uva Integral Campo Largo 1.5L")
                    .categoria(CategoriaProduto.BEBIDAS)
                    .codigoBarras("7895000000777")
                    .preco(new BigDecimal("16.80"))
                    .estoque(35)
                    .descricao("Suco 100% fruta sem adição de açúcares nem conservantes.")
                    .dataCadastro(LocalDateTime.now().minusDays(2))
                    .build(),

                Produto.builder()
                    .nome("Pão Francês Tradicional (kg)")
                    .categoria(CategoriaProduto.PADARIA)
                    .codigoBarras("7896000000888")
                    .preco(new BigDecimal("18.90"))
                    .estoque(50)
                    .descricao("Pão francês crocante assado na hora com farinha especial.")
                    .dataCadastro(LocalDateTime.now().minusDays(1))
                    .build()
            );

            repository.saveAll(produtosIniciais);
            System.out.println("✅ [DataInitializer] Carga inicial de 8 produtos realizada com sucesso na tabela TDS_MVC_TB_MERCADO!");
        }
    }
}
