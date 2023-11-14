pipeline {
    environment {
        dockerImageName = "ski"
       DOCKER_IMAGE_TAG = "v${BUILD_NUMBER}" // Using Jenkins BUILD_NUMBER as the tag
       dockerTag = 'latest'
                           nexusRegistryUrl = '192.168.56.2:8082/repository/ski/'
                           dockerUsername = 'admin'
                           dockerPassword = '191JFT2516'
    }
    agent any
    stages {
        stage('GIT') {
            steps {
                echo "Getting Project from Git"
                git branch: 'sahar',
                    url: 'https://github.com/saharzouari/SKI'
            }
        }
         stage('Build') {
            steps {
                script {
                    sh "mvn --version" // Use the specified Maven installation
                    sh "mvn clean package -DskipTests" // Build your Maven project
                }
            }
        }

  stage('JUnit / Mockito Tests') {
                            steps {
                                // Run JUnit and Mockito tests using Maven
                                sh 'mvn test'
                            }
                        }
stage('SonarQube ') {
             steps {
                    sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=191JFT2516'
                   }
             }
stage('Deploy') {
                    steps {
                           sh 'mvn deploy -DskipTests=true'
                                }
                            }
        stage("Build Docker image") {
            steps {
                script {
                    sh "docker build -t $dockerImageName:$DOCKER_IMAGE_TAG -f Dockerfile ./"
                }
            }
        }

       stage('dockerhub') {
                                          steps {

                                     sh "docker login -u saharzouari -p 191JFT2516"
                                     sh "docker tag $dockerImageName:$DOCKER_IMAGE_TAG saharzouari/ski:$DOCKER_IMAGE_TAG"
                                     sh "docker push  saharzouari/ski:$DOCKER_IMAGE_TAG"
                                          }
                    }


        stage("Start app and db") {
            steps {
              sh "docker login -u saharzouari -p 191JFT2516"
                sh "docker-compose up -d"
            }
        }




}

// deploymentRepo

}
