# Etapa 1: Build do projeto
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app

# Copiar arquivos do projeto para a imagem
COPY pom.xml .
COPY src ./src

# Compilar e empacotar o JAR
RUN mvn clean package -DskipTests

# Etapa 2: Configuração da imagem final
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copiar o JAR do build anterior
COPY --from=builder /app/target/reservas-restaurantes-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta usada pela aplicação
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
