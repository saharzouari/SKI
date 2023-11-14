pipeline {
    environment {
        dockerImageName = "ski"
       DOCKER_IMAGE_TAG = "v${BUILD_NUMBER}" // Using Jenkins BUILD_NUMBER as the tag
       def dockerTag = 'latest'
                           def nexusRegistryUrl = '172.17.0.5:8082/repository/ski/'
                           def dockerUsername = 'admin'
                           def dockerPassword = '191JFT2516'
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

     stage("Deploy Docker Image to private registry") {
            steps {
                script {


                    sh "docker build -t ${dockerImage}:${dockerTag} ."
                    sh "docker tag ${dockerImage}:${dockerTag} ${nexusRegistryUrl}${dockerImage}:${dockerTag}"
                    sh "docker login -u ${dockerUsername} --password-stdin ${nexusRegistryUrl}"
                    sh "docker push ${nexusRegistryUrl}${dockerImage}:${dockerTag}"
                }

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
