CREATE TABLE empresa
(
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    razao_social  VARCHAR(150) NOT NULL,
    nome_fantasia VARCHAR(150),
    cnpj          VARCHAR(20)  NOT NULL
);

CREATE TABLE filial
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    empresa_id UUID         NOT NULL REFERENCES empresa (id),
    nome       VARCHAR(150) NOT NULL
);

CREATE TABLE pessoa
(
    id                UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    empresa_id        UUID         NOT NULL REFERENCES empresa (id),
    nome_completo     VARCHAR(150) NOT NULL,
    cpf_cnpj          VARCHAR(20),
    tipo_papel        VARCHAR(50)  NOT NULL, -- ENUM: CLIENTE, FORNECEDOR, FUNCIONARIO
    data_nascimento   DATE,
    nacionalidade     VARCHAR(50),
    estado_civil      VARCHAR(30),
    telefone          VARCHAR(20),
    email_contato     VARCHAR(100),
    endereco_completo TEXT
);

-- ==========================================
-- TABELAS (ACESSOS E RH)
-- ==========================================

CREATE TABLE utilizador
(
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    filial_id     UUID         NOT NULL REFERENCES filial (id),
    pessoa_id     UUID REFERENCES pessoa (id),
    email_login   VARCHAR(100) NOT NULL,
    senha_hash    VARCHAR(255) NOT NULL,
    perfil_acesso VARCHAR(50)  NOT NULL, -- "Ex: ADMIN, CAIXA, RH"
    ativo         BOOLEAN          DEFAULT TRUE
);

CREATE TABLE dados_trabalhistas
(
    pessoa_id              UUID PRIMARY KEY REFERENCES pessoa (id), -- É 1:1, a PK é a própria FK
    rg_numero              VARCHAR(50),
    titulo_eleitor         VARCHAR(50),
    pis_pasep              VARCHAR(50),
    ctps_numero            VARCHAR(50),
    banco_agencia          VARCHAR(20),
    banco_conta            VARCHAR(50),
    grau_escolaridade      VARCHAR(100),
    certificado_reservista VARCHAR(50),
    data_admissao          DATE,
    aso_valido             BOOLEAN,
    url_pasta_documentos   TEXT
);

CREATE TABLE dependente
(
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    pessoa_id       UUID         NOT NULL REFERENCES pessoa (id),
    nome_completo   VARCHAR(150) NOT NULL,
    cpf             VARCHAR(20),
    data_nascimento DATE,
    grau_parentesco VARCHAR(50)  NOT NULL
);

-- ==========================================
-- TABELAS (CATÁLOGO E ESTOQUE FÍSICO)
-- ==========================================
CREATE TABLE categoria
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    empresa_id UUID         NOT NULL REFERENCES empresa (id),
    nome       VARCHAR(100) NOT NULL
);

CREATE TABLE item
(
    id                    UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    empresa_id            UUID           NOT NULL REFERENCES empresa (id),
    categoria_id          UUID REFERENCES categoria (id),
    tipo                  VARCHAR(20)    NOT NULL, -- PRODUTO ou SERVICO
    sku                   VARCHAR(50),
    codigo_barras         VARCHAR(50),
    nome                  VARCHAR(150)   NOT NULL,
    marca                 VARCHAR(100),
    preco_venda_padrao    DECIMAL(15, 4) NOT NULL,
    custo_medio_ponderado DECIMAL(15, 4)   DEFAULT 0,
    controla_estoque      BOOLEAN          DEFAULT TRUE,
    ncm                   VARCHAR(10),
    cest                  VARCHAR(10),
    origem                VARCHAR(50),
    unidade_medida        VARCHAR(10),
    peso_bruto            DECIMAL(10, 3),
    estoque_minimo        DECIMAL(10, 3)   DEFAULT 0,
    estoque_maximo        DECIMAL(10, 3),
    dimensoes             JSONB                    -- JSON nativo do PostgreSQL para largura, altura, comprimento
);

CREATE TABLE estoque_filial
(
    id                 UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    filial_id          UUID NOT NULL REFERENCES filial (id),
    item_id            UUID NOT NULL REFERENCES item (id),
    saldo_atual        DECIMAL(15, 4)   DEFAULT 0,
    localizacao_fisica VARCHAR(100)
);

-- ==========================================
-- TABELAS (RASTREABILIDADE E PDV)
-- ==========================================
CREATE TABLE lote
(
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    item_id             UUID           NOT NULL REFERENCES item (id),
    numero_lote         VARCHAR(50)    NOT NULL,
    data_fabricacao     DATE,
    data_validade       DATE,
    quantidade_original DECIMAL(15, 4) NOT NULL,
    saldo_disponivel    DECIMAL(15, 4) NOT NULL
);

CREATE TABLE movimentacao_estoque
(
    id                       UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    filial_id                UUID           NOT NULL REFERENCES filial (id),
    item_id                  UUID           NOT NULL REFERENCES item (id),
    lote_id                  UUID REFERENCES lote (id),
    utilizador_id            UUID           NOT NULL REFERENCES utilizador (id),
    pessoa_id                UUID REFERENCES pessoa (id),
    tipo_movimento           VARCHAR(20)    NOT NULL, -- ENTRADA ou SAIDA
    motivo_operacao          VARCHAR(50)    NOT NULL,
    documento_origem         VARCHAR(100),
    quantidade               DECIMAL(15, 4) NOT NULL,
    saldo_anterior           DECIMAL(15, 4) NOT NULL,
    saldo_atual              DECIMAL(15, 4) NOT NULL,
    valor_unitario_movimento DECIMAL(15, 4) NOT NULL,
    impostos_rateados        DECIMAL(15, 4)   DEFAULT 0,
    data_hora                TIMESTAMP        DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE terminal_caixa
(
    id                 UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    filial_id          UUID        NOT NULL REFERENCES filial (id),
    nome_identificador VARCHAR(50) NOT NULL,
    mac_address_uuid   VARCHAR(100),
    ativo              BOOLEAN          DEFAULT TRUE
);

CREATE TABLE turno_caixa
(
    id                       UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    terminal_caixa_id        UUID        NOT NULL REFERENCES terminal_caixa (id),
    utilizador_id            UUID        NOT NULL REFERENCES utilizador (id),
    data_hora_abertura       TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    data_hora_fechamento     TIMESTAMP,
    saldo_inicial            DECIMAL(15, 2)   DEFAULT 0,
    saldo_calculado_sistema  DECIMAL(15, 2)   DEFAULT 0,
    saldo_declarado_operador DECIMAL(15, 2),
    status                   VARCHAR(30) NOT NULL -- ABERTO, FECHADO, COM_QUEBRA
);

-- ==========================================
-- TABELAS (OPERAÇÃO DE CAIXA E VENDA)
-- ==========================================

CREATE TABLE movimentacao_caixa
(
    id             UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    turno_caixa_id UUID           NOT NULL REFERENCES turno_caixa (id),
    utilizador_id  UUID REFERENCES utilizador (id), -- Autorizador (opcional)
    tipo_operacao  VARCHAR(50)    NOT NULL,         -- SANGRIA, SUPRIMENTO
    valor          DECIMAL(15, 2) NOT NULL,
    justificativa  TEXT,
    data_hora      TIMESTAMP        DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE venda
(
    id                    UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    turno_caixa_id        UUID           NOT NULL REFERENCES turno_caixa (id),
    pessoa_id             UUID REFERENCES pessoa (id), -- Cliente (NULL se consumidor final não identificado)
    status                VARCHAR(30)    NOT NULL,     -- PENDENTE, CONCLUIDA, CANCELADA
    valor_bruto           DECIMAL(15, 2) NOT NULL,
    descontos             DECIMAL(15, 2)   DEFAULT 0,
    acrescimos            DECIMAL(15, 2)   DEFAULT 0,
    valor_liquido         DECIMAL(15, 2) NOT NULL,
    data_hora_venda       TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,

    -- Dados Fiscais (NFC-e)
    status_fiscal         VARCHAR(30)      DEFAULT 'NENHUM',
    chave_nfce            VARCHAR(44),
    protocolo_autorizacao VARCHAR(100),
    recibo_lote           VARCHAR(100)
);

CREATE TABLE item_venda
(
    id                    UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    venda_id              UUID           NOT NULL REFERENCES venda (id),
    item_id               UUID           NOT NULL REFERENCES item (id),
    quantidade            DECIMAL(15, 4) NOT NULL,
    preco_unitario_tabela DECIMAL(15, 4) NOT NULL,
    preco_praticado       DECIMAL(15, 4) NOT NULL,
    valor_total_item      DECIMAL(15, 2) NOT NULL
);

CREATE TABLE pagamento_venda
(
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    venda_id        UUID           NOT NULL REFERENCES venda (id),
    forma_pagamento VARCHAR(50)    NOT NULL, -- DINHEIRO, PIX, CREDITO, DEBITO
    valor_pago      DECIMAL(15, 2) NOT NULL,
    nsu_transacao   VARCHAR(100),
    bandeira_cartao VARCHAR(50)
);