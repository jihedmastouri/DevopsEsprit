pipeline {
    agent any
    stages {
         stage('Maven Test'){
			steps: {
				sh """mvn test"""
			}
		}
         stage('Maven Build'){
			steps: {
				sh """mvn clean install"""
			}
		}
         stage('Docker Run'){
			steps: {
				sh """docker-compose up"""
			}
		}
    }
}
