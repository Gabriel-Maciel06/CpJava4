-- ===================================================================
-- SCRIPT DE CONFIGURAÇÃO E CRIAÇÃO DO BANCO DE DADOS ORACLE
-- PROJETO: Mercado Express API - Checkpoint 4 (FIAP)
-- AUTOR: Gabriel Maciel (RM562795) & Grupo
-- ===================================================================

-- 1. Remoção preventiva (Drop Table & Sequence)
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE TDS_TB_MERCADO CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -942 THEN RAISE; END IF;
END;
/

BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE SQ_TDS_MERCADO';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -2289 THEN RAISE; END IF;
END;
/

-- 2. Criação da Sequence para Auto-Incremento do ID
CREATE SEQUENCE SQ_TDS_MERCADO
   START WITH 1
   INCREMENT BY 1
   NOCACHE
   NOCYCLE;

-- 3. Criação da Tabela TDS_TB_MERCADO
CREATE TABLE TDS_TB_MERCADO (
    ID NUMBER(19) DEFAULT SQ_TDS_MERCADO.NEXTVAL PRIMARY KEY,
    NOME VARCHAR2(100) NOT NULL,
    TIPO VARCHAR2(50) NOT NULL,
    SETOR VARCHAR2(50) NOT NULL,
    TAMANHO VARCHAR2(30) NOT NULL,
    PRECO NUMBER(10, 2) NOT NULL,
    CONSTRAINT CK_PRECO_POSITIVO CHECK (PRECO > 0)
);

-- 4. Inserção de Dados Iniciais de Exemplo (Mercado Express)
INSERT INTO TDS_TB_MERCADO (NOME, TIPO, SETOR, TAMANHO, PRECO)
VALUES ('Detergente Liquido Neutro 500ml', 'Limpeza', 'Higiene e Limpeza', '500ml', 3.49);

INSERT INTO TDS_TB_MERCADO (NOME, TIPO, SETOR, TAMANHO, PRECO)
VALUES ('Meia Esportiva Algodão Kit c/ 3', 'Vestuário', 'Bazar', 'G (39-43)', 24.90);

INSERT INTO TDS_TB_MERCADO (NOME, TIPO, SETOR, TAMANHO, PRECO)
VALUES ('Maçã Fuji Selecionada 1kg', 'Hortifruti', 'Frutas e Verduras', '1kg', 8.99);

INSERT INTO TDS_TB_MERCADO (NOME, TIPO, SETOR, TAMANHO, PRECO)
VALUES ('Carrinho Controle Remoto Turbo', 'Brinquedos', 'Infantil', 'Unico', 79.90);

INSERT INTO TDS_TB_MERCADO (NOME, TIPO, SETOR, TAMANHO, PRECO)
VALUES ('Desinfetante Pinho Fresco 1L', 'Limpeza', 'Higiene e Limpeza', '1L', 7.20);

COMMIT;

-- 5. Consulta de Validação
SELECT * FROM TDS_TB_MERCADO ORDER BY ID ASC;
