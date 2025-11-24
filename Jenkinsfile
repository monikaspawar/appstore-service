pipeline {
    agent any

    environment {
        GIT_TOKEN = credentials('git-token')     // GitHub token stored in Jenkins
    }

    stages {

        stage('Clone Code') {
            steps {
                git branch: 'master', url: "https://${GIT_TOKEN}@github.com/monikaspawar/appstore-service.git"
            }
        }

        stage('Build Maven Project') {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }

        stage('Run Tests') {
            steps {
                sh "mvn test"
            }
        }

        stage('Deploy to EC2 Server') {
            steps {
                sshagent(['ec2-key']) {     // SSH key credential stored in Jenkins
                    sh """
                    scp target/*.jar ubuntu@52.66.144.167:/home/ubuntu/appstore-service.jar
                    ssh ubuntu@52.66.144.167 "bash /home/ubuntu/deploy.sh"
                    """
                }
            }
        }
    }
}

