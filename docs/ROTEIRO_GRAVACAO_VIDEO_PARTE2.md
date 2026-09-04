# 🎬 Roteiro de Apresentação e Gravação de Vídeo (~5 Minutos)
## FIAP Checkpoint 4 (Parte 2) — Spring Web MVC, Security & Deploy
### Professor: Marcel • Projeto: Mercado Express

---

### 📋 Preparação Antes de Iniciar a Gravação
1. **Aba 1 do Navegador:** Link da Aplicação Web em Produção / Local:  
   👉 `http://localhost:8090` ou `http://mercado-express-rm562795.chilecentral.azurecontainer.io:8090`
2. **Aba 2 do Navegador:** Repositório no GitHub aberto no `README.md` (`https://github.com/Gabriel-Maciel06/CpJava4.git`).
3. **IDE (IntelliJ IDEA):** Projeto aberto mostrando os pacotes `controller`, `dto`, `model`, `repository`, `service`, `config` e a pasta `templates/`.

---

### ⏱️ CRONOGRAMA MINUTO A MINUTO (5 MINUTOS)

---

### 🎙️ BLOCO 1: Abertura, Equipe e Contextualização do Tema (00:00 - 00:50)
**O que mostrar na tela:** Repositório no GitHub com a lista de integrantes e os links.

**O que falar:**
> "Olá, professor Marcel! Sou o Gabriel Maciel, RM562795, e em conjunto com meu grupo formado pela Vitória, Augusto, Thomas e Matheus, estamos apresentando a **Parte 2 do Checkpoint 4 de Java e Spring Web** da FIAP.
> 
> Mantendo o mesmo tema da Parte 1 da empresa **Mercado Express** — voltada para itens de conveniência como meias, produtos de limpeza, hortifruti, brinquedos, bebidas e padaria —, desenvolvemos nesta Parte 2 uma aplicação completa em **Spring MVC**, com renderização server-side em **Thymeleaf**, segurança com **Spring Security 6**, persistência na tabela `TDS_MVC_TB_MERCADO` e deploy publicado na nuvem."

---

### 🎙️ BLOCO 2: Segurança com Spring Security e Perfis de Acesso (00:50 - 01:50)
**O que mostrar na tela:** Navegador na tela de login e depois no Dashboard.

**O que falar e fazer:**
1. **Intercepção de rota privada:**
   > "Veja que, ao tentar acessar diretamente a rota `/produtos` sem autenticação, o Spring Security intercepta a requisição e nos redireciona para a tela de login estilizada em Thymeleaf com Bootstrap 5.3."
2. **Login com usuário `operador` (`operador` / `operador123`):**
   > "Vou me autenticar inicialmente com o usuário `operador`, que possui o perfil `ROLE_USER`."
3. **Dashboard e Métricas:**
   > "Ao entrar, somos recebidos pelo Dashboard de gestão do Mercado Express, que consulta a tabela no banco e traz métricas em tempo real: Total de produtos cadastrados, itens em estado crítico de estoque baixo (menor ou igual a 5 unidades) e o valor patrimonial total inventariado."

---

### 🎙️ BLOCO 3: Demonstração Prática do CRUD Completo com Thymeleaf (01:50 - 03:40)
**O que mostrar na tela:** Catálogo de Produtos (`/produtos`), Formulário de Cadastro e Edição.

**O que falar e fazer:**
1. **Listagem e Filtros Dinâmicos (READ):**
   > "Acessando o Catálogo de Produtos, temos a tabela interativa renderizada com Thymeleaf, destacando badges visuais coloridos para cada categoria — como Higiene e Limpeza em azul, Hortifruti em verde, Vestuário em roxo e Brinquedos em laranja —, além do alerta visual de estoque. Podemos pesquisar por nome ou filtrar por categoria."
2. **Cadastro com Validação Jakarta (CREATE):**
   > "Vou clicar em **Cadastrar Novo Produto**. Se eu tentar submeter o formulário em branco, as anotações do **Jakarta Bean Validation** (`@NotBlank`, `@Positive`, `@Min`) são disparadas no servidor e o Thymeleaf exibe as mensagens de erro em linha para o usuário.  
   > Agora preencho corretamente: *'Sabão em Barra Ypê 5x200g'*, categoria *Higiene e Limpeza*, preço *R$ 11,90*, estoque *60*, e salvo. O item é persistido no banco e a mensagem de sucesso aparece via Flash Message."
3. **Edição de Produto (UPDATE):**
   > "Vou editar o produto que acabamos de cadastrar, ajustando o estoque para *75 unidades* e o preço para *R$ 12,50*. Salvo e a tabela já reflete a alteração imediatamente."
4. **Visualização Detalhada (READ):**
   > "No botão de detalhes (ícone do olho), acessamos a ficha técnica completa do produto com o código de barras EAN-13, data de cadastro e descrição."

---

### 🎙️ BLOCO 4: Controle de Acesso Restrito & Exclusão de Registro (03:40 - 04:30)
**O que mostrar na tela:** Logout e login como `admin` para exclusão.

**O que falar e fazer:**
1. **Permissão do Operador:**
   > "Como o operador possui apenas `ROLE_USER`, o botão de exclusão não fica visível para ele por segurança via taglib `sec:authorize`. Se ele tentar forçar a URL de exclusão, é barrado na tela 403 de Acesso Negado."
2. **Login com Administrador (`admin` / `admin123`):**
   > "Faço o logout e entro agora com o usuário `admin` (`ROLE_ADMIN`). Agora o botão de exclusão física está disponível. Clico em excluir, o modal de confirmação em JavaScript solicita validação, e ao confirmar, o registro é deletado com sucesso do banco de dados."

---

### 🎙️ BLOCO 5: Estrutura do Código na IDE e Encerramento (04:30 - 05:00)
**O que mostrar na tela:** IntelliJ IDEA mostrando os pacotes e a classe `SecurityConfig.java`.

**O que falar:**
> "Para concluir, aqui no IntelliJ IDEA temos o projeto modularizado em camadas: Controllers MVC com injeção por construtor, DTOs com validação, Entities JPA mapeando a tabela `TDS_MVC_TB_MERCADO`, templates Thymeleaf com fragments de layout reutilizáveis e a classe `SecurityConfig` com proteção CSRF ativa.
> 
> Todos os arquivos, prints do Spring Initializr, documentação e links de deploy na nuvem estão disponíveis no nosso repositório no GitHub. Muito obrigado, professor Marcel!"

---

✅ **Duração Total: ~5 minutos | Apresentação 100% alinhada aos critérios de avaliação!** 🚀
