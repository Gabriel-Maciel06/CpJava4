# 🛒 Mercado Express API — FIAP Checkpoint 4 (Parte 1)

> **API RESTful de Alta Maturidade (HATEOAS Nível 3) para Gestão de Estoque do Mercado Express**  
> **Framework:** Spring Boot 3.3 | **Linguagem:** Java 21 | **Persistência:** Spring Data JPA + Oracle DB / H2  
> **IDE Utilizada:** IntelliJ IDEA Ultimate Edition

---

## 👥 Integrantes do Grupo e RMs

| Nome do Integrante | RM |
| :--- | :--- |
| **Gabriel Maciel Alves de Oliveira** | **RM562795** |
| **Vitória Rodrigues Martins** | **RM565160** |
| **Augusto Bonomo Júnior** | **RM565155** |
| **Thomas Fontes** | **RM562254** |
| **Matheus Pereira Molina** | **RM563399** |

* **Repositório GitHub:** [https://github.com/Gabriel-Maciel06/CpJava4.git](https://github.com/Gabriel-Maciel06/CpJava4.git)
* **Link de Deploy da API:** [http://40.87.31.123:8082/swagger-ui.html](http://40.87.31.123:8082/swagger-ui.html)

---

## 📝 Descrição do Projeto

O **Mercado Express API** foi desenvolvido para atender às demandas de redes de mercados express de conveniência (venda de produtos de limpeza, hortifruti, vestuário/bazar, brinquedos, higiene e alimentos). 

A aplicação oferece um conjunto completo de endpoints **CRUD** (Create, Read, Update, Delete), implementando o **Nível 3 do Modelo de Maturidade de Richardson (HATEOAS)**, permitindo que clientes da API naveguem dinamicamente pelos recursos por meio de hipermídias (`_links`).

---

## 🛠️ Tecnologias e Ecossistema Spring Utilizados

- **Java 21 LTS** — Recursos modernos da linguagem.
- **Spring Boot 3.3.4 (Maven)** — Framework base para rápida configuração de microsserviços.
- **Spring HATEOAS** — Adição de navegação hipermídia aos recursos RESTful.
- **Spring Data JPA & Hibernate** — Abstração e ORM para manipulação do banco de dados Oracle.
- **Lombok** — Produtividade com anotações (`@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@Builder`).
- **Spring Validation (Jakarta Validation)** — Validação declarativa de entrada de dados (`@NotBlank`, `@NotNull`, `@Positive`).
- **Oracle JDBC Driver (OJDBC11) / H2 Database** — Suporte ao banco Oracle FIAP com fallback transparente H2 em memória.
- **OpenAPI 3 / Swagger UI** — Documentação e testes interativos no navegador (`http://localhost:8082/swagger-ui.html`).
- **Docker & Docker Compose** — Conteinerização para deploy em nuvem na VM Azure.

---

## 🗄️ Estrutura da Tabela do Banco de Dados (`TDS_TB_MERCADO`)

A persistência de dados é realizada na tabela `TDS_TB_MERCADO` do banco Oracle FIAP.

```sql
CREATE TABLE TDS_TB_MERCADO (
    ID NUMBER(19) DEFAULT SQ_TDS_MERCADO.NEXTVAL PRIMARY KEY,
    NOME VARCHAR2(100) NOT NULL,
    TIPO VARCHAR2(50) NOT NULL,
    SETOR VARCHAR2(50) NOT NULL,
    TAMANHO VARCHAR2(30) NOT NULL,
    PRECO NUMBER(10, 2) NOT NULL,
    CONSTRAINT CK_PRECO_POSITIVO CHECK (PRECO > 0)
);
```

### Mapeamento dos Atributos
- **Id (`ID`)**: Identificador único numérico de incremento automático via `SQ_TDS_MERCADO`.
- **Nome (`NOME`)**: Descrição/nome comercial do produto (ex: Detergente Líquido 500ml).
- **Tipo (`TIPO`)**: Categoria do item (ex: Limpeza, Alimentos, Vestuário, Brinquedos).
- **Setor (`SETOR`)**: Seção do mercado (ex: Higiene e Limpeza, Hortifruti, Bazar, Infantil).
- **Tamanho (`TAMANHO`)**: Especificação de porte/conteúdo (ex: 500ml, 1kg, M, 30cm, Único).
- **Preço (`PRECO`)**: Valor unitário em reais (`BigDecimal`).

---

## 📡 Endpoints da API e Exemplos do CRUD

> **Servidor Local:** `http://localhost:8082`  
> **Porta Obrigatória:** `8082`

---

### 1️⃣ `GET /mercado` — Listar Todos os Produtos (com HATEOAS)
Retorna todos os produtos cadastrados com links HATEOAS.

**Requisição:**
`GET http://localhost:8082/mercado`

**Resposta (`200 OK`):**
```json
{
  "_embedded": {
    "produtos": [
      {
        "id": 1,
        "nome": "Detergente Liquido Neutro 500ml",
        "tipo": "Limpeza",
        "setor": "Higiene e Limpeza",
        "tamanho": "500ml",
        "preco": 3.49,
        "_links": {
          "self": {
            "href": "http://localhost:8082/mercado/1"
          },
          "todos-produtos": {
            "href": "http://localhost:8082/mercado"
          },
          "atualizar": {
            "href": "http://localhost:8082/mercado/1"
          },
          "atualizar-parcial": {
            "href": "http://localhost:8082/mercado/1"
          },
          "deletar": {
            "href": "http://localhost:8082/mercado/1"
          }
        }
      },
      {
        "id": 2,
        "nome": "Meia Esportiva Algodão Kit c/ 3",
        "tipo": "Vestuário",
        "setor": "Bazar",
        "tamanho": "G (39-43)",
        "preco": 24.90,
        "_links": {
          "self": {
            "href": "http://localhost:8082/mercado/2"
          },
          "todos-produtos": {
            "href": "http://localhost:8082/mercado"
          },
          "atualizar": {
            "href": "http://localhost:8082/mercado/2"
          },
          "atualizar-parcial": {
            "href": "http://localhost:8082/mercado/2"
          },
          "deletar": {
            "href": "http://localhost:8082/mercado/2"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://localhost:8082/mercado"
    }
  }
}
```

---

### 2️⃣ `GET /mercado/{id}` — Consultar Produto por ID
Retorna os detalhes de um produto específico.

**Requisição:**
`GET http://localhost:8082/mercado/1`

**Resposta (`200 OK`):**
```json
{
  "id": 1,
  "nome": "Detergente Liquido Neutro 500ml",
  "tipo": "Limpeza",
  "setor": "Higiene e Limpeza",
  "tamanho": "500ml",
  "preco": 3.49,
  "_links": {
    "self": {
      "href": "http://localhost:8082/mercado/1"
    },
    "todos-produtos": {
      "href": "http://localhost:8082/mercado"
    },
    "atualizar": {
      "href": "http://localhost:8082/mercado/1"
    },
    "atualizar-parcial": {
      "href": "http://localhost:8082/mercado/1"
    },
    "deletar": {
      "href": "http://localhost:8082/mercado/1"
    }
  }
}
```

---

### 3️⃣ `POST /mercado` — Cadastrar Novo Produto (Create)
Cadastra um novo produto no banco de dados.

**Requisição:**
`POST http://localhost:8082/mercado`  
*Header:* `Content-Type: application/json`

**JSON de Entrada:**
```json
{
  "nome": "Carrinho Controle Remoto Turbo",
  "tipo": "Brinquedos",
  "setor": "Infantil",
  "tamanho": "Único",
  "preco": 79.90
}
```

**Resposta (`201 Created`):**
*Header Location:* `http://localhost:8082/mercado/4`
```json
{
  "id": 4,
  "nome": "Carrinho Controle Remoto Turbo",
  "tipo": "Brinquedos",
  "setor": "Infantil",
  "tamanho": "Único",
  "preco": 79.90,
  "_links": {
    "self": {
      "href": "http://localhost:8082/mercado/4"
    },
    "todos-produtos": {
      "href": "http://localhost:8082/mercado"
    },
    "atualizar": {
      "href": "http://localhost:8082/mercado/4"
    },
    "atualizar-parcial": {
      "href": "http://localhost:8082/mercado/4"
    },
    "deletar": {
      "href": "http://localhost:8082/mercado/4"
    }
  }
}
```

---

### 4️⃣ `PUT /mercado/{id}` — Atualizar Produto Completo (Update)
Substitui todas as informações de um produto existente.

**Requisição:**
`PUT http://localhost:8082/mercado/1`  
*Header:* `Content-Type: application/json`

**JSON de Entrada:**
```json
{
  "nome": "Detergente Líquido Neutro Concentrado 500ml",
  "tipo": "Limpeza",
  "setor": "Higiene e Limpeza",
  "tamanho": "500ml",
  "preco": 4.19
}
```

**Resposta (`200 OK`):**
```json
{
  "id": 1,
  "nome": "Detergente Líquido Neutro Concentrado 500ml",
  "tipo": "Limpeza",
  "setor": "Higiene e Limpeza",
  "tamanho": "500ml",
  "preco": 4.19,
  "_links": {
    "self": {
      "href": "http://localhost:8082/mercado/1"
    },
    "todos-produtos": {
      "href": "http://localhost:8082/mercado"
    },
    "atualizar": {
      "href": "http://localhost:8082/mercado/1"
    },
    "atualizar-parcial": {
      "href": "http://localhost:8082/mercado/1"
    },
    "deletar": {
      "href": "http://localhost:8082/mercado/1"
    }
  }
}
```

---

### 5️⃣ `PATCH /mercado/{id}` — Atualização Parcial
Altera pontualmente um ou mais atributos de um produto (ex: reajuste de preço).

**Requisição:**
`PATCH http://localhost:8082/mercado/1`  
*Header:* `Content-Type: application/json`

**JSON de Entrada:**
```json
{
  "preco": 3.89
}
```

**Resposta (`200 OK`):**
```json
{
  "id": 1,
  "nome": "Detergente Líquido Neutro Concentrado 500ml",
  "tipo": "Limpeza",
  "setor": "Higiene e Limpeza",
  "tamanho": "500ml",
  "preco": 3.89,
  "_links": {
    "self": {
      "href": "http://localhost:8082/mercado/1"
    },
    "todos-produtos": {
      "href": "http://localhost:8082/mercado"
    },
    "atualizar": {
      "href": "http://localhost:8082/mercado/1"
    },
    "atualizar-parcial": {
      "href": "http://localhost:8082/mercado/1"
    },
    "deletar": {
      "href": "http://localhost:8082/mercado/1"
    }
  }
}
```

---

### 6️⃣ `DELETE /mercado/{id}` — Excluir Produto (Delete)
Remove um registro do banco pelo ID.

**Requisição:**
`DELETE http://localhost:8082/mercado/1`

**Resposta (`204 No Content`):**
*(Corpo vazio)*

---

## 🚀 Como Executar o Projeto Localmente

### Pré-requisitos
- JDK 21+ instalado
- Apache Maven 3.8+

### Passos:
1. Clone este repositório:
   ```bash
   git clone https://github.com/Gabriel-Maciel06/CpJava4.git
   cd CpJava4/mercado-express-api
   ```
2. Compile e execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```
3. A API estará acessível em `http://localhost:8082/mercado`.
4. Documentação Swagger UI: `http://localhost:8082/swagger-ui.html`
5. Console do Banco H2 (Modo Dev): `http://localhost:8082/h2-console`  
   - JDBC URL: `jdbc:h2:mem:mercadodb`
   - User: `sa` | Password: *(vazio)*

---

## ☁️ Deploy e Nuvem

A API foi conteinerizada via **Docker** e está configurada para deploy automatizado em servidor VM Azure.
- **URL da API em Produção:** `http://40.87.31.123:8082/swagger-ui.html`

---

## 📸 Configuração do Spring Initializr

Abaixo está o registro da configuração inicial do projeto com suas respectivas dependências:

![Spring Initializr Dependencies](SpringInitializr_dependencies.png)
