pipeline {
    agent { label 'Agent' }

    stages {

        stage("Git Fetch") {
            steps {
                git credentialsId: 'githubid',
                    url: 'https://github.com/L0geshK/InWarrentyFlowAPIFrameWork.git'
            }
        }

        stage("Build Docker Image") {
            steps {
                sh 'docker build -t inwarrenty-api-tests .'
            }
        }

        stage("InWarrenty API Parallel Test Execution") {
            parallel {

                stage("QA Suite") {
                    steps {
                        sh """
                        docker run --rm \
                        -v \$(pwd)/target:/app/target \
                        inwarrenty-api-tests \
                        test -DsuiteXmlFile=testng.xml \
                             -Denv=qa \
                             -Dallure.results.directory=target/allure-results-qa
                        """
                    }
                }

                stage("DEV Suite") {
                    steps {
                        sh """
                        docker run --rm \
                        -v \$(pwd)/target:/app/target \
                        inwarrenty-api-tests \
                        test -DsuiteXmlFile=testng.xml \
                             -Denv=dev \
                             -Dallure.results.directory=target/allure-results-dev
                        """
                    }
                }
            }
        }

        stage("Verify Allure Results") {
            steps {
                sh "ls -R target"
            }
        }
    }

    post {
        always {
            allure(
                commandline: 'allure',
                results: [
                    [path: 'target/allure-results-qa'],
                    [path: 'target/allure-results-dev']
                ]
            )
        }
    }
}
