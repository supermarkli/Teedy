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

        stage('PMD') {
            steps {
                // 运行PMD分析
                bat 'mvn pmd:pmd'
            }
        }

        stage('Test') {
            steps {
                // 运行测试
                bat 'mvn test'
            }
            post {
                always {
                    // 存储测试结果
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }



        stage('Generate Reports') {
            steps {
                // 生成Surefire测试报告
                bat 'mvn surefire-report:report'
                // 生成Javadoc
                bat 'mvn javadoc:javadoc'
            }
        }
    }

    post {
        always {
            // 存储构建的artifacts，包括jar, war, surefire报告和javadoc
            archiveArtifacts artifacts: '**/target/**/*.jar, **/target/**/*.war, **/target/surefire-reports/*.xml, **/target/site/apidocs/**/*', fingerprint: true, allowEmptyArchive: true
        }
    }
}
