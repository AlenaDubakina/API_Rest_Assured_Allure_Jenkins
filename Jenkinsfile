pipeline {
    agent any

    triggers {
        cron('H 8 * * 1-5')
    }

    tools {
        maven 'Maven_3'
        jdk 'JDK_25'
    }

    stages {

         stage('Checkout') {
                    steps {
                        echo "Geting the project from GitHub"
                        git url: 'https://github.com/AlenaDubakina/API_Rest_Assured_Allure_Jenkins', branch: 'main'
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
        results: [[path: 'target/allure-results']
        echo "Pipeline finished"
        }

       success {
                   echo 'Pipeline succeeded!'
                   notifyTelegram("SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}")
       }
       failure {
                   echo 'Pipeline failed.'
                   notifyTelegram("FAIlED: ${env.JOB_NAME} #${env.BUILD_NUMBER}")
       }
       always {
                   cleanWs()
       }
    }
}