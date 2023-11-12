pipeline {
    agent any

    environment {
        SONAR_TOKEN = credentials('Sonar_Token')
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-leila')

    }

    stages {
        stage('Maven Clean & Build') {
            steps {
                script {
                    def mavenCmd = 'mvn clean compile'
                    sh mavenCmd
                }
            }
        }

        stage('JUnit / Mockito Tests') {
            steps {
                script {
                    def mavenCmd = 'mvn test'
                    sh mavenCmd
                }
            }
        }

        stage('SonarQube') {
            steps {
                script {
                    def mavenCmd = "mvn verify sonar:sonar -Dsonar.login=$SONAR_TOKEN"
                    sh mavenCmd
                }
            }
        }



        stage('Nexus Deploy') {
            steps {
                script {
                    def mavenCmd = 'mvn deploy -Dmaven.test.skip=true'
                    sh mavenCmd
                }
            }
        }
         stage('Build docker image') {
                    steps {
                         sh " docker build -t leilabencheikh/stationski-1.1.0 ."
                    }
                }
                stage('login to dockerhub') {
                    steps{
                        sh 'echo $DOCKERHUB_CREDENTIALS_PSW | sudo docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                    }
                }


               stage('push image') {
                    steps{
                        sh 'sudo docker push leilabencheikh/stationski-1.1.0 '
                    }
                }




    }
}
