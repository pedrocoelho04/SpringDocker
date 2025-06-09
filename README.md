# API Comp/Con para Frames de Lancer
Este projeto é uma API RESTful desenvolvida com Spring Boot para gerir dados de Frames do jogo de RPG de mesa "Lancer". A API fornece endpoints CRUD (Create, Read, Update, Delete) para consultar e manipular informações sobre os diferentes chassis de mechas disponíveis no jogo.

A aplicação é totalmente "containerizada" com Docker, permitindo que seja executada em qualquer ambiente de forma simples e consistente.

## **Tecnologias Utilizadas**
**Backend**: Java 11

**Framework**: Spring Boot 2.7.18

**Persistência de Dados**: Spring Data JPA (Hibernate)

**Base de Dados**: SQLite

**Gestão de Dependências e Build**: Maven (utilizando o Maven Wrapper)

**Containerização**: Docker e Docker Compose

**Documentação da API**: SpringDoc (Swagger UI)

**Utilitários**: Lombok

## **Pré-requisitos**
Para executar este projeto, apenas duas ferramentas são necessárias:

- **Git**: Para clonar o repositório.

- **Docker Desktop**: Para construir e executar a aplicação em um contêiner. O Docker irá gerir o ambiente Java, Maven e a base de dados.

## **Como Executar a Aplicação**
A forma mais simples de executar o projeto é utilizando o Docker Compose, que orquestra todo o processo de build e execução.

- **Passo 1: Clonar o Repositório**

Abra um terminal e clone o projeto para a sua máquina local:

```
git clone <URL_DO_SEU_REPOSITÓRIO_GIT>
cd <NOME_DA_PASTA_DO_PROJETO>
```

- **Passo 2: Subir a Aplicação com Docker Compose**

Na pasta raiz do projeto (onde se encontra o ficheiro compose.yaml), execute o seguinte comando:
```
docker-compose up --build
```

### O que este comando faz?

```--build```: Instrui o Docker a construir a imagem da sua aplicação pela primeira vez, seguindo os passos definidos no Dockerfile.

```up```: Inicia o contêiner da aplicação após o build.

Na primeira execução: O Maven irá descarregar todas as dependências do projeto (pode demorar alguns minutos). Nas execuções seguintes, o processo será muito mais rápido.

```DataSeeder```: Quando a aplicação iniciar pela primeira vez, a classe ```DataSeeder``` irá verificar se a base de dados está vazia e, se estiver, irá populá-la com os dados iniciais dos Frames.

A sua API estará em execução e pronta para receber pedidos!

**Para parar a aplicação**: Pressione Ctrl + C no terminal onde o Docker Compose está a ser executado.

## **Endpoints da API e Documentação**
A API vem com uma documentação interativa (Swagger UI) que permite visualizar e testar todos os endpoints diretamente no navegador.

**URL da Documentação (Swagger UI)**:
http://localhost:8080/swagger-ui.html

Exemplos de Endpoints Principais:
- ```GET /api/frames```

  - Retorna uma lista de todos os Frames na base de dados.

- ```GET /api/frames/{id}```

  - Retorna um Frame específico pelo seu ID numérico (ex: 1, 2, etc.).

- ```GET /api/frames/by_id/{frames_id}```

  - Retorna um Frame específico pelo seu ID de negócio (ex: gpc_genghis).

- ```GET /api/traits```

  - Retorna uma lista de todos os Traits.

## Base de Dados
A aplicação utiliza uma base de dados SQLite.

O ficheiro da base de dados (compcon.db) é gerado na pasta sqlite-data/ na raiz do projeto, graças ao mapeamento de volume definido no compose.yaml.

Por padrão, a pasta sqlite-data/ está incluída no .gitignore para não ser enviada para o repositório, seguindo as melhores práticas.
