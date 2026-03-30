pipeline {
    agent {
            docker {
                image 'maven:3.9-eclipse-temurin-17'
                args '-v /var/run/docker.sock:/var/run/docker.sock'
            }
    }

      environment {
            BASE_URI = 'https://jsonplaceholder.typicode.com'
        }

    triggers {
        cron('H 8 * * 1-5')
    }

    stages {

         stage('Checkout') {
                    steps {
                        echo "Geting the project from GitHub"
                        git url: 'https://github.com/AlenaDubakina/API_Rest_Assured_Allure_Jenkins', branch: 'main'
                    }
         }
        stage('Generate api.properties') {
                    steps {
                        script {
                            writeFile(
                        file: 'src/test/resources/config/api.properties',
                        text: """api.base.uri=${BASE_URI}"""
                            )
                        }
                        echo 'Файл src/test/resources/config/api.properties успешно создан.'
                    }
        }

         stage('Build & Test') {
            steps {
                echo "Running"
                sh 'mvn clean test'
            }
         }
    }

    post {
        always {
        echo "Allure report generation"
        allure includeProperties: false,
        reportBuildPolicy: 'ALWAYS',
        results: [[path: 'target/allure-results']]
        echo "Pipeline finished"
        }

       success {
            echo 'Pipeline succeeded!'
       }
       failure {
            echo 'Pipeline failed.'
       }
       cleanup {
            echo 'Cleaning workspace...'
            cleanWs()
       }
    }
}