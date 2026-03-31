pipeline {
    agent any

    environment {
        BASE_URI = 'https://jsonplaceholder.typicode.com'
    }

    triggers {
        cron('H 8 * * 1-5')
    }

    stages {

        stage('Checkout') {
            steps {
                echo "Getting project from GitHub"
                git url: 'https://github.com/AlenaDubakina/API_Rest_Assured_Allure_Jenkins', branch: 'main'
            }
        }

        stage('Generate api.properties') {
            steps {
                script {
                    writeFile(
                        file: 'src/test/resources/config/api.properties',
                        text: "api.base.uri=${BASE_URI}"
                    )
                }
                echo 'api.properties created'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building Docker image..."
                sh 'docker build -t api-tests .'
            }
        }

        stage('Run Tests') {
            steps {
                echo "Running tests..."

                sh '''
                mkdir -p target/allure-results

                docker run --rm \
                -v $WORKSPACE/target/allure-results:/app/target/allure-results \
                api-tests
                '''
            }
        }
    }

    post {
        always {
            echo "Generating Allure report"

            sh 'ls -la target/allure-results || true'

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