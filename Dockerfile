FROM openjdk:11-jre-slim    

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia os arquivos de configuração do Maven
COPY pom.xml .

# Baixa as dependências (isso aproveita o cache do Docker se as dependências não mudarem)
RUN mvn dependency:go-offline

# Copia todo o código fonte do projeto
COPY src ./src

# Executa o build, criando o arquivo .jar. O -DskipTests pula os testes durante o build do Docker.
RUN mvn package -DskipTests