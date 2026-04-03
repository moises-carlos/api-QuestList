# Estágio de Build (Compilação)
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio de Execução (Rodar a API)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/QuestList-1.0-SNAPSHOT.jar app.jar

# Expõe a porta que o Render vai usar
EXPOSE 8080

# Comando para iniciar o Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"] 
