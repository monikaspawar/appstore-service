pipeline {
    agent any

    tools {
        maven 'mvn'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'master', credentialsId: 'git' , url: 'git@github.com:monikaspawar/appstore-service.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
            post {
                success {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Build Jar') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Deploy to Server') {
            steps {
                sh '''
                sudo systemctl stop your-app || true
                cp target/*.jar /opt/app/app.jar
                sudo systemctl start your-app
                '''
            }
        }
    }
}
