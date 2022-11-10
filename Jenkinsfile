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
		 stage('Build image') {
            steps {
        		 sh "docker build -t jihedmastouri/devops-spring ."
        	}
        }  
		 stage('Push image') {
  			steps {
  			    withDockerRegistry([ credentialsId: "dockerhub", url: "" ]) {
					sh "docker push jihedmastouri/devops-spring"
				}
			}
        }
         stage('Run'){
			steps {
				sh """docker-compose up -d"""
			}
		}
		 stage('Sending email') {
	        steps {
	             mail bcc: '', body: '''Hello from Firas,
	             Devops Pipeline with success.
	             Cordialement''', cc: '', from: '', replyTo: '', subject: 'Devops', to: 'firas.gacha@esprit.tn'
	        }
	    }
    }
}
