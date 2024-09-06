pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numTokeepStr: '1'))
    }
    stages {
        stage('build') {
            steps {
                echo "heloo!world"
            }
        }
    }
}