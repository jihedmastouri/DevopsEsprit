pipeline {
    agent any
    stages {
         stage('Maven Build'){
			steps {
				sh """mvn clean install -DskipTests=true"""
			}
		}
         stage('Maven SonarQube'){
			steps {
				sh """ mvn sonar:sonar \
				  -Dsonar.projectKey=achat \
				  -Dsonar.host.url=http://192.168.56.55:9000 \
				  -Dsonar.login=787f991df9d2fd521738bff5553f288bb8adfbee
				"""
			}
		}
         stage('Docker Run'){
			steps {
				sh """docker-compose up"""
			}
		}
    }
}
