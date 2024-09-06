// Implementing CICD for spring3 project
// This includes Build,Neuxs,Tomcat
pipeline {
    agent any
    tools {
        maven 'mymaven'

    }
    stages {
        stage ('Clone') {
            steps {
                // want to clone a repo from git hub
               // sh 'git clone https://github.com/saaji1224/jenkins-project.git'
                git branch: 'main', credentialsId: 'Git_Hub', url: 'https://github.com/saaji1224/jenkins-project.git'
            }
        }
        stage ('Build') {
            steps {
                sh 'mvn --version'
                sh 'mvn clean package -Dmaven.test.failure.ignore=true'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.war', followSymlinks: false
                }
            }
        }
        stage ('Deploy to Tomcat') {
            steps {
                // sh 'curl user_name:passowrd source destination'
                sh 'curl -v -u saacademy:password -T /var/lib/jenkins/workspace/Jenkins_Project/target/spring3-mvc-maven-xml-hello-world-1.0-SNAPSHOT.war http://34.145.55.59:8080/manager/text/deploy?path=/spring-cloud'
            }
        }
    }
}