FROM openjdk:8-jdk-alpine

LABEL maintainer="manuel.leibetseder@students.fh-hagenberg.at"

COPY ./build/libs/doodoo-backend.jar /app/

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "doodoo-backend.jar"]
