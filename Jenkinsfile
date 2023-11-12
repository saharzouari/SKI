pipeline {
    agent any

    environment {
        SONAR_TOKEN = credentials('Sonar_Token')

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




    }
}
