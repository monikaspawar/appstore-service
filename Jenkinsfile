pipeline {
    agent any

    tools {
        maven 'mvn'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'master', credentialsId: 'git' , url: 'https://github.com/monikaspawar/appstore-service.git'
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

        stage('Deploy locally') {
            steps {
                sh '''
                systemctl stop your-app || true
                mkdir -p /opt/app
                cp target/appstore-service-0.0.1-SNAPSHOT.jar /opt/app/app.jar
                systemctl start your-app
                '''
            }
        }
    }
}
