pipeline {
    agent any

    tools {
        // Configure a JDK installation named 'JDK26' in Jenkins Global Tool Configuration.
        jdk 'JDK26'
        maven 'Maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Run UI Tests') {
            steps {
                sh 'mvn clean test -Dbrowser=chrome -Dheadless=true'
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/TEST-*.xml'
            archiveArtifacts allowEmptyArchive: true, artifacts: 'screenshots/**/*.png'
        }
    }
}
