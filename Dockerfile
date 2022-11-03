FROM openjdk:8
COPY ./target/achat-1.0.jar achat-1.0.jar
CMD ["java","-jar","achat-1.0.jar"]