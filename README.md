# 💰 MyFinance – API REST + Aplicação Web

MyFinance é uma aplicação web para **gerenciamento financeiro pessoal**, desenvolvida com **Java EE (Servlets)** no back-end e **HTML/CSS/JavaScript puro** no front-end, com auxílio do Bootstrap. A aplicação permite o **registro, consulta, edição e exclusão de transações financeiras**, além de exibir um **painel de balanço financeiro com gráficos interativos**.

---

## 🔧 Tecnologias Utilizadas

### 🔙 Back-End (API REST)
- Java EE com Servlets
- Padrões: Front Controller, Factory, Chain of Responsibility, Command, MVC
- JDBC com JNDI (MySQL)
- JSON (entrada e saída)
- Biblioteca Gson (serialização)
- Servidor Apache Tomcat

### 🔜 Front-End (Aplicação Cliente)
- HTML5, CSS3, JavaScript (puro)
- Bootstrap 5
- Chart.js (visualização de dados)

---

## 📌 Funcionalidades da API (Requisitos Funcionais)

| Código | Descrição |
|--------|-----------|
| [RF1] | Cadastrar transações com descrição, valor, tipo (RECEITA/DESPESA), categoria e data |
| [RF2] | Atualizar e excluir transações já registradas |
| [RF3] | Consultar uma transação específica pelo ID |
| [RF4] | Listar transações com paginação e filtros por mês, ano, tipo e categoria |
| [RF5] | Retornar um resumo financeiro com: total de receitas, despesas por categoria e saldo atual |

---

## 🌐 Funcionalidades da Aplicação Cliente (Front-End)

| Código | Descrição |
|--------|-----------|
| [RF1] | Visualizar todas as transações registradas com os seguintes elementos: descrição, valor, tipo, categoria e data. Com **diferenciação visual** entre receitas e despesas |
| [RF2] | Adicionar uma nova transação via formulário interativo |
| [RF3] | Editar ou excluir transações, com atualização dinâmica da listagem |
| [RF4] | Exibir **painel financeiro** com total de receitas/despesas por categoria, saldo atual e gráficos comparativos |

---

## 📊 Painel Financeiro (Dashboard)

- Gráficos de pizza (Chart.js) para distribuição de receitas e despesas por categoria
- Gráfico de barras para comparação total entre receitas e despesas
- Cards de saldo atual, total de receitas e despesas
- Painel responsivo e informativo

---