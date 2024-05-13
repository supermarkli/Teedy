pipeline {
    agent any

    stages {
        stage('Checkout SCM') {
            steps {
                // 检出源代码
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // 使用Maven构建项目，跳过测试
                bat 'mvn -B -DskipTests clean package'
            }
        }
        stage('Doc') {
            steps {
                bat 'mvn javadoc:jar --fail-never'
            }
        }
        stage('Pmd') {
            steps {
                // 运行PMD分析
                bat 'mvn pmd:pmd'
            }
        }

        stage('Test report') {
            steps {
                bat 'mvn surefire-report:report'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/site/**', fingerprint: true
            archiveArtifacts artifacts: '**/target/**/*.jar', fingerprint: true
            archiveArtifacts artifacts: '**/target/**/*.war', fingerprint: true
        }
    }
}
