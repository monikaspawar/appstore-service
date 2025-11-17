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
                mkdir -p $WORKSPACE/deploy
                cp target/appstore-service-0.0.1-SNAPSHOT.jar $WORKSPACE/deploy/app.jar
                echo "Application deployed to $WORKSPACE/deploy/app.jar"
                '''
            }
        }
    }
}