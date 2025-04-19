# Etapa 1: Construção do JAR
FROM maven:3.9.9-amazoncorretto-21-debian AS builder

WORKDIR /app

# Copia os arquivos do projeto para o contêiner
COPY . .

# Executa o build do projeto sem rodar os testes
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final para execução da aplicação
FROM amazoncorretto:21

WORKDIR /app

# Copia o JAR gerado na etapa anterior
COPY --from=builder /app/target/*.jar minha-api.jar

# Expõe a porta 8080
EXPOSE 8080

# Define o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "minha-api.jar"]