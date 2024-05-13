pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'supermarkli/teedytest2024:latest'
        DOCKER_HUB_CREDS = '12110504' // 替换为您的 Jenkins 凭证 ID
    }

    stages {

        stage('Package') { 
            steps {
              checkout scmGit(branches: [[name: '*/master']], extensions: [], 
userRemoteConfigs: [[url: 'https://github.com/supermarkli/Teedy.git']])
              sh 'mvn -B -DskipTests clean package' 
            }
        }

        stage('Build Docker Image') {
            steps {
                // 构建 Docker 镜像，确保 Dockerfile 使用的是包含应用的 jar 或 war 文件
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('', DOCKER_HUB_CREDS) {
                        sh "docker push ${DOCKER_IMAGE}"
                    }
                }
            }
        }

        stage('Update K8s Deployment') {
            steps {
                sh 'kubectl set image deployment/hello-node teedytest2024=${DOCKER_IMAGE}'
            }
        }

    }

 
}
