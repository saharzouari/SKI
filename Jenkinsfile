pipeline {

    agent any

    environment {
         DOCKER_REGISTRY = ''
         DOCKER_IMAGE_NAME = 'chamesebh/gestion-station-ski'
         DOCKER_IMAGE_TAG = 'latest'
         DOCKERHUB_CREDENTIALS = credentials('dockerhub-token')
    }
    stages {
        stage('GIT') {
            steps {
                git branch: 'ChamsEddine', url: 'https://github.com/saharzouari/SKI.git'
            }
        }

        stage('Maven Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

         stage('Mockito & Junit Tests') {
            steps {
                sh "mvn test"
            }
        }
         stage('SONARQUBE') {
             steps {
                 sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
             }
         }
         stage('Nexus Deploy') {
             steps {
                    sh 'mvn deploy -Dmaven.test.skip=true'
             }
          }
         stage('Build docker image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} ."
            }
        }
         stage('Push Image to Docker Hub') {
            steps {
                script {
                    sh 'docker login -u chamesebh -p 25226726Cc'
                    sh 'docker push ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}'
                    }
                }
         }
          stage('Docker Compose') {
            steps{
                sh ' docker compose up -d'
            }
        }
}
}

