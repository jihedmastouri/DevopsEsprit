pipeline {
    agent any
    stages {
         stage('Compile'){
			steps {
				sh """mvn clean compile"""
			}
		}
         stage('SonarQube'){
			steps {
				sh """ mvn sonar:sonar \
				  -Dsonar.projectKey=achat \
				  -Dsonar.host.url=http://192.168.56.55:9000 \
				  -Dsonar.login=787f991df9d2fd521738bff5553f288bb8adfbee
				"""
			}
		}
         stage('Testing'){
			steps {
				sh """mvn test"""
			}
		}
         stage('Nexus'){
			steps {
				sh """mvn deploy -DskipTests"""
			}
		}
         stage('Run'){
			steps {
				sh """docker-compose up -d"""
			}
		}
    }
}
