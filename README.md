# Desafio - Sistema de relatório de pedidos 📊

## 🎯 Objetivo do Desafio
O objetivo deste desafio é praticar a refatoração, aplicar boas práticas de design de código e utilizar design patterns em um projeto Java. Os alunos devem transformar um código base que processa um arquivo CSV de pedidos em um sistema mais modular, organizado e de fácil manutenção.

## 📝 Tarefas
1. **Refatoração do Código:**
    - Melhorar a legibilidade e a estrutura do código.
    - Aplicar princípios de Orientação a Objetos, como encapsulamento, herança e polimorfismo.
    - Utilizar boas práticas de desenvolvimento, como a separação de responsabilidades e a redução de duplicação de código.
> **DIRETRIZES:**
> - Use recursos do Java 8, ou superior (_Streams, lambdas, Optional, java.time, etc_).
> - Aplique _design patterns_ onde fizer sentido.
> - Modularize o projeto em pacotes e classes coesas para melhorar a estrutura e a manutenção.
> - Discuta com o instrutor e os colegas sobre suas refatorações.

2. **Melhorias:**
    - Refatore a lógica de leitura e processamento de arquivos CSV para usar a biblioteca [OpenCSV](https://www.baeldung.com/opencsv).

3. **Novos formatos:**
    - Nosso projeto precisa ser mais flexível quanto ao formato de arquivo para processamento de pedidos. Adapte para suportar o processamento de arquivos JSON e XML. Os arquivos estão na pasta `src/main/resources`.
> Use a biblioteca [Jackson](https://www.baeldung.com/jackson-object-mapper-tutorial) para facilitar seu trabalho.


### Resultado Esperado

Ao final do desafio, espera-se que os alunos tenham um código mais limpo, modular e de fácil manutenção, aplicando os conceitos de Orientação a Objetos, boas práticas de desenvolvimento e design patterns.

---

# Informações gerais dos pedidos em _pedidos.csv_
- Campos: **CATEGORIA, PRODUTO, PREÇO, QUANTIDADE, DATA, CLIENTE**

## RELATÓRIO DE VALORES TOTAIS
- TOTAL DE PEDIDOS REALIZADOS: 16
- TOTAL DE PRODUTOS VENDIDOS: 35
- TOTAL DE CATEGORIAS: 5
- MONTANTE DE VENDAS: R$ 178.374,49
- PEDIDO MAIS BARATO: R$ 95,17 (Clean Code)
- PEDIDO MAIS CARO: R$ 55.056,00 (iPhone 13 Pro)

### ▶️ Execute a classe `Main.java` para gerar o relatório de pedidos.