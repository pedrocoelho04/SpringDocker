# --- Estágio 1: Build da Aplicação com Maven e JDK ---
# Usamos uma imagem oficial do Maven com JDK 11.
FROM maven:3.8.5-openjdk-11 AS build

# Define o diretório de trabalho dentro do contêiner de build
WORKDIR /app

# Copia o pom.xml para aproveitar o cache de dependências
COPY pom.xml .

# Baixa as dependências
RUN mvn dependency:go-offline

# Copia o resto do código fonte
COPY src ./src

# Executa o build para criar o .jar
# O -DskipTests pula os testes
RUN mvn package -DskipTests


# --- Estágio 2: Imagem Final de Execução com JRE ---
FROM openjdk:11-jre-slim

# Define o diretório de trabalho na imagem final
WORKDIR /app

# Expõe a porta em que a aplicação Spring Boot roda
EXPOSE 8080

# Cria o diretório para o banco de dados SQLite (para o volume)
RUN mkdir ./database

# Copia APENAS o arquivo .jar do estágio 'build' para a imagem final
COPY --from=build /app/target/*.jar app.jar

# Comando para iniciar a aplicação quando o contêiner for executado
# Força o banco a ser criado na pasta mapeada pelo volume
ENTRYPOINT ["java", "-jar", "-Dspring.datasource.url=jdbc:sqlite:/app/database/compcon.db", "app.jar"]