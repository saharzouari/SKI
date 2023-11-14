pipeline {
    environment {
        dockerImageName = "ski"
       DOCKER_IMAGE_TAG = "v${BUILD_NUMBER}" // Using Jenkins BUILD_NUMBER as the tag
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

        stage("Build Docker image") {
            steps {
                script {
                    sh "docker build -t $dockerImageName -f Dockerfile ./"
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
/*
stage("Deploy to private registry") {
    steps {
        script {
            def nexusRegistryUrl = '172.20.10.1:8082/'




            // Build the Docker image
            sh "docker build -t $dockerImageName:$DOCKER_IMAGE_TAG ."

            // Tag the Docker image
            sh "docker tag $dockerImageName:$DOCKER_IMAGE_TAG ${nexusRegistryUrl}$dockerImageName:$DOCKER_IMAGE_TAG"

            // Log in to the private registry

            sh "echo ${dockerPassword} | docker login --username ${dockerUsername} --password-stdin ${nexusRegistryUrl}"

            // Push the Docker image to the private registry
            sh "docker push ${nexusRegistryUrl}$dockerImageName:$DOCKER_IMAGE_TAG"
        }
    }
}*/
        stage("Start app and db") {
            steps {
              sh "docker login -u saharzouari -p 191JFT2516"
                sh "docker-compose up -d"
            }
        }



       stage('Deploy') {
                    steps {
                           sh 'mvn deploy -DskipTests=true'
                                }
                            }


// deploymentRepo
    post {
        always {
            cleanWs()
        }

    }
}
