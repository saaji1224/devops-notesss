// tools
pipeline {
    agent any
    tools {
        maven 'mymaven'

    }
    stages {
        stage ("maven") {
            steps {
                echo "hello welocme to maven"
                sh "mvn --version"
            }
        }
    }
}

//
pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    stages {
        stage ("Maven") {
            steps {
                echo "hello,welcome to maven"
                sh "mvn --version"
            }
        }
        stage ("other maven") {
            tools {
                jdk 'jdk17'
            }
            steps {
                echo "maven version with jdk 17"
                sh "mvn --version"
            }
        }
    }
}