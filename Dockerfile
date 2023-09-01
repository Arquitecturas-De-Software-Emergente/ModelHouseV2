# Utiliza la imagen base de Maven para construir la aplicación
FROM maven:3.8.4-openjdk-17 AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos de configuración de Maven (pom.xml) al contenedor
COPY pom.xml .

# Descarga las dependencias de Maven
RUN mvn dependency:go-offline -B

# Copia el resto de los archivos del proyecto al contenedor
COPY src ./src

# Compila la aplicación
RUN mvn package -DskipTests

# Crea una nueva imagen usando la imagen base de Java 17
FROM openjdk:17-jdk

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado por Maven desde la imagen de compilación
COPY --from=build /app/target/ModelHouse-0.0.1-SNAPSHOT.war ModelHouse-0.0.1-SNAPSHOT.war

# Expone el puerto en el que se ejecutará tu aplicación
EXPOSE 8080

# Comando para ejecutar tu aplicación Spring Boot
CMD ["java", "-jar", "ModelHouse-0.0.1-SNAPSHOT.war"]