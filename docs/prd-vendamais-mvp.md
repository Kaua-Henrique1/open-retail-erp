# PRD — VendaMais ERP (MVP)

> **Status:** rascunho para aprovação da equipe
> **Escopo:** épicos PDV-001 a PDV-006
> **Próximo passo:** com este PRD aprovado, sai a spec técnica (`docs/spec-vendamais-mvp.md`)

---

## 1. O problema

A loja pequena de roupas e utilidades vende no balcão com caderno, calculadora ou um sistema que não acompanha o ritmo do caixa. Isso gera quatro dores do dia a dia:

- **A fila anda devagar.** O operador digita código por código ou procura o produto pelo nome. Quando o cliente paga parte no Pix e parte em dinheiro, a conta do troco é feita de cabeça.
- **O estoque mente.** A mercadoria chega com nota fiscal, mas a entrada é digitada à mão, item por item, quando sobra tempo. Peça rasgada, quebrada ou vencida sai da prateleira e continua no sistema.
- **O dono não sabe o que vende.** Para saber o faturamento da semana ou o que está parado há meses, ele soma cupons ou confia na memória.
- **Ninguém confia no número.** Como entrada, saída e perda não ficam registradas, a contagem física nunca bate e ninguém sabe dizer por quê.

Hoje o contorno é retrabalho: contar a prateleira no fim do mês, refazer a conta do caixa no fechamento e comprar de novo o que ainda estava no depósito.

## 2. Quem usa e quando

| Papel | Momento | O que precisa |
|---|---|---|
| **Operador de caixa** | No balcão, com fila, durante todo o expediente | Registrar a venda sem tirar a mão do leitor e fechar o pagamento sem errar o troco |
| **Estoquista / conferente** | Na chegada do fornecedor, com as caixas abertas | Dar entrada na mercadoria a partir da nota fiscal, sem redigitar |
| **Gerente / dono (empresário)** | No fim do dia ou antes de fazer pedido ao fornecedor | Ver quanto vendeu, o que está acabando e o que está parado |

## 3. Como medimos sucesso

Ainda não temos medição do processo atual. Os valores de hoje precisam ser levantados com uma loja piloto **antes** da implementação; sem eles, os números abaixo não servem de comparação.

| Métrica | Como medir | Hoje | Meta |
|---|---|---|---|
| Tempo para registrar uma venda de 5 itens | Cronômetro do primeiro bipe à confirmação do pagamento | a medir | a definir com a loja piloto |
| Tempo de entrada de uma nota de 20 itens | Do recebimento das caixas ao estoque atualizado | a medir | a definir |
| Diferença entre estoque do sistema e contagem física | Contagem de uma categoria, mensal | a medir | a definir |
| Vendas com troco errado no fechamento | Divergência do caixa por turno | a medir | zero divergência causada pelo cálculo do sistema |

## 4. O que entra nesta versão — histórias de usuário

Cada história corresponde a um épico já aberto no GitHub, com uma issue de backend e outra de frontend.

### HU-01 · Consultar a posição do estoque (PDV-001)

**Como** gerente, **quero** ver o estoque atual filtrado por categoria e por situação (abaixo do mínimo ou normal) **para** decidir o que repor antes de faltar na prateleira.

**Critérios de aceite**
- O gerente filtra por categoria (Roupas, Utilidades) e por situação do estoque.
- Produtos abaixo da quantidade mínima aparecem destacados.
- A lista é paginada e mostra o total de itens e de peças em estoque.
- Só gerente e dono acessam o relatório.

### HU-02 · Acompanhar vendas e produtos parados (PDV-002)

**Como** dono, **quero** ver faturamento, número de vendas e ticket médio por período, além dos produtos sem venda há X dias, **para** saber se a loja está indo bem e o que precisa de promoção.

**Critérios de aceite**
- Períodos disponíveis: hoje, últimos 7 dias e um intervalo escolhido.
- O faturamento soma o que foi **cobrado**, não preço × quantidade.
- O período respeita o fuso da loja: a venda das 23h fica no dia certo.
- A lista de produtos parados mostra há quantos dias cada um está sem venda.

### HU-03 · Dar entrada na mercadoria pelo XML da nota (PDV-003)

**Como** estoquista, **quero** enviar o XML da NF-e do fornecedor e conferir os itens antes de confirmar **para** atualizar o estoque sem digitar produto por produto.

**Critérios de aceite**
- Ao enviar o XML, o sistema mostra uma prévia dos itens e ainda não altera o estoque.
- Cada item da nota é casado com o produto pelo código de barras (EAN). Item sem correspondência aparece sinalizado.
- O estoque só muda quando o estoquista confirma.
- A mesma nota não pode ser lançada duas vezes.

### HU-04 · Registrar a baixa de produto avariado (PDV-004)

**Como** gerente, **quero** tirar do estoque um produto danificado, vencido ou perdido, informando o motivo, **para** que o sistema reflita o que realmente está na prateleira.

**Critérios de aceite**
- A baixa exige produto, quantidade e motivo, escolhido em uma lista fechada.
- A baixa não pode deixar o estoque negativo.
- Toda baixa fica registrada com quem fez, quando e por quê, e esse registro não pode ser apagado.
- A redução do estoque e o registro acontecem juntos: ou os dois, ou nenhum.

### HU-05 · Bipar produtos sem parar (PDV-005)

**Como** operador de caixa, **quero** passar os produtos no leitor de código de barras um atrás do outro **para** atender a fila sem tocar no mouse.

**Critérios de aceite**
- O campo de código de barras nunca perde o foco depois de adicionar um item.
- O item aparece no carrinho sem recarregar a página.
- Bipar o mesmo produto de novo aumenta a quantidade em vez de criar outra linha.
- Código não encontrado toca um som de erro e mostra um aviso claro, sem travar o caixa.

### HU-06 · Receber em mais de uma forma de pagamento (PDV-006)

**Como** operador de caixa, **quero** dividir o pagamento entre dinheiro, Pix, débito e crédito **para** aceitar a forma que o cliente tiver, sem fazer conta de cabeça.

**Critérios de aceite**
- O operador adiciona quantas formas de pagamento precisar, cada uma com seu valor.
- O saldo restante é atualizado a cada valor informado.
- Troco só existe para pagamento em **dinheiro** e é calculado pelo sistema.
- O botão de finalizar fica bloqueado enquanto houver saldo restante.
- O servidor confere que a soma dos pagamentos cobre o total. O valor calculado no navegador não é aceito sem essa conferência.
- Um clique duplo em "Finalizar" não gera duas vendas.

### Pré-requisitos técnicos (sem história própria)

- **PDV-007 · Autenticação e perfis (JWT/RBAC):** as histórias HU-01, HU-02 e HU-04 dependem dos perfis de operador, gerente e dono.
- **PDV-008 · Tratamento padronizado de erros:** a HU-05 depende de um 404 limpo para "produto não encontrado". Em nenhum caso o usuário pode ver stack trace ou tela em branco.

## 5. O que fica FORA desta versão

Fica fora para que a versão termine. Cada item pode virar épico próprio depois.

- **Emissão de NFC-e / cupom fiscal** e integração com a SEFAZ. O sistema registra a venda, mas não emite documento fiscal.
- **Integração com maquininha (TEF) e Pix dinâmico.** O operador informa a forma de pagamento; o sistema não fala com a adquirente.
- **Cancelamento, devolução e troca de venda pelo PDV.** O status existe na API, mas o fluxo de tela não entra.
- **Desconto, cupom e promoção.**
- **Cadastro de clientes, fiado e crediário.**
- **Abertura e fechamento de caixa** (sangria, suprimento, conferência do turno).
- **Mais de uma loja ou filial.**
- **Variação de produto por grade** (tamanho e cor como SKU filho).
- **Pedido de compra ao fornecedor.**
- **Funcionamento offline** e aplicativo mobile.
- **Exportação de relatórios** (PDF, Excel).

## 6. Riscos e decisões já tomadas

### Decisões fechadas (não reabrir)

| Decisão | Motivo |
|---|---|
| Backend em Java 21 + Spring Boot 4, PostgreSQL 16 e frontend em Angular | Stack definida pela equipe e já configurada no repositório |
| Todas as rotas sob `/api/v1` | Rotas citadas em issues antigas (`/api/estoque/...`, `/api/produtos/...`) estão obsoletas |
| Formas de pagamento: dinheiro, crédito, débito e Pix | Enum `PaymentMethod` já definido no contrato |
| Venda exige o header `Idempotency-Key` | É o que impede o clique duplo de gerar cobrança dupla |
| Preço unitário gravado no item da venda | Mudar o preço no cadastro não pode reescrever vendas passadas |
| Produto com venda é desativado, nunca excluído | Excluir apagaria a origem do histórico de vendas e de estoque |

### Riscos

| Risco | Impacto | Como mitigar |
|---|---|---|
| Leitor de código de barras envia `Enter` antes do código completo ou digita rápido demais | Itens perdidos ou duplicados no carrinho | Testar com leitor físico desde a primeira entrega da HU-05 |
| XML de fornecedores com EAN ausente ou `SEM GTIN` | Entrada da nota trava ou casa com o produto errado | A prévia sinaliza o item sem correspondência e o estoquista resolve manualmente |
| Duas vendas simultâneas do último item | Estoque negativo | Baixa condicional no banco: só vende se ainda houver saldo |
| Valores em ponto flutuante | Centavo sumindo no total e no troco | `BigDecimal` no backend e cálculo em centavos inteiros no frontend |
| Fuso do servidor (UTC) diferente do fuso da loja | Venda da noite cai no dia seguinte do relatório | Todo período de relatório declara o fuso explicitamente |
| Autenticação ainda liberada em `/api/v1/**` | Qualquer pessoa acessa relatórios e baixa | PDV-007 precisa entrar antes de qualquer uso real |

## 7. Perguntas em aberto

1. Existe uma loja piloto para levantar os números da seção 3?
2. Qual é o prazo de entrega do MVP (calendário da disciplina ou da equipe)?
3. Quais motivos de baixa entram na lista fechada? Sugestão: avaria, vencimento, furto ou perda, e uso interno.
4. O operador de caixa também pode fazer a baixa de avaria, ou só o gerente?
