pipeline{
    agent any

    tools {
        maven 'Maven 3.8.7'
    }

    environment {
        PATH = "/usr/local/bin:${env.PATH}"
        DOCKERHUB_CREDENTIALS_ID = 'Docker-Hub'
        DOCKERHUB_REPO = 'kak3r/sum-product_fx'
        DOCKER_IMAGE_TAG = 'latest'

        // DB config for tests
        DB_HOST = 'host.docker.internal'
        DB_PORT = '3306'
        DB_NAME = 'calc_data'
        DB_USER = 'root'
        DB_PASSWORD = 'Test12'
    }


    stages {
        stage('Setup Maven') {
            steps {
                script {
                    def mvnHome = tool name: 'Maven 3.8.7', type: 'maven'
                    env.PATH = "${mvnHome}/bin:${env.PATH}"
                }
            }
        }

        stage('check') {
            steps {
                git url: 'https://github.com/Kasanen/week7_calculator_fx_db-main.git', branch: 'main'
            }
        }


        stage('Build') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn clean package -DskipTests'
                    } else {
                        bat 'mvn clean package -DskipTests'
                    }
                }
            }
        }

        stage('Test') {
    steps {
        script {
            if (isUnix()) {
                sh 'echo "DB_HOST=$DB_HOST DB_PORT=$DB_PORT DB_NAME=$DB_NAME"; mvn test'
            } else {
                bat 'echo DB_HOST=%DB_HOST% DB_PORT=%DB_PORT% DB_NAME=%DB_NAME% && mvn test'
            }
        }
    }
}

        stage('Build Docker Image') {
            steps {
                script {
                    if (isUnix()) {
                        sh "docker build -t ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG} ."
                    } else {
                        bat "docker build -t ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG} ."
                    }
                }
            }
        }


        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', env.DOCKERHUB_CREDENTIALS_ID) {
                        docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                    }
                }
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            jacoco execPattern: '**/target/jacoco.exec'
        }
    }

}