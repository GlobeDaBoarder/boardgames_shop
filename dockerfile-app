FROM maven:3.6.3-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/boardgames_shop-0.0.1-SNAPSHOT.jar /app
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.5.0/wait /wait
RUN chmod +x /wait
EXPOSE 8080
CMD /wait && java -jar boardgames_shop-0.0.1-SNAPSHOT.jar
