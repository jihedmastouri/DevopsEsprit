FROM openjdk:8
RUN curl -u admin:root -o achat.jar "http://192.168.56.55:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar" -L
CMD ["java","-jar","achat.jar"]
