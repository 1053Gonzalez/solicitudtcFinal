# Imagen base
FROM eclipse-temurin:17.0.11_9-jdk

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Instala Maven
RUN apt-get update && apt-get install -y maven

COPY ./pom.xml /app/
COPY ./.mvn /app/.mvn
COPY ./mvnw /app/

# Descarga las dependencias
RUN chmod +x mvnw
RUN ./mvnw clean package
RUN ./mvnw dependency:go-offline

# Copia el codigo fuente dentro del contenedor
COPY ./src /app/src

# Exponer el puerto
EXPOSE 8080

# Compilar el proyecto y omitir las pruebas
RUN ./mvnw clean install -DskipTests

# Iniciar la aplicación cuando el contenedor se inicie
ENTRYPOINT ["java", "-jar", "/app/target/solicitudtc-0.0.1-SNAPSHOT.jar"]
