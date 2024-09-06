// Implementing CICD for spring3 project
// This includes Build,Neuxs,Tomcat
pipeline {
    agent any
    tools {
        maven 'mymaven'

    }
    environment {
        TOMCAT_CREDS = credentials('tomcat_cred') //this is having user name and password
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
            tools {
                maven 'mymaven'
                jdk 'jdk17'
            }
            steps {
                sh 'mvn --version'
                sh 'mvn clean package -Dmaven.test.failure.ignore=true'
                //sh 'mvn deploy' pom.xml distibution managment and settings.xml 
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.war', followSymlinks: false
                }
            }
        }
        stage ('Nexus') {
            steps {
                script {
                    // i want to read pom.xml using readMavenPom step
                    // https://www.jenkins.io/doc/pipeline/steps/pipeline-utility-steps/#readmavenpom-read-a-maven-project-file
                    pom = readMavenPom file: "pom.xml";
                    fileNames = findFiles(glob: "target/*.${pom.packaging}");
                    //just for testing purpose printing down various agruments
                    echo "${fileNames[0].name} ${fileNames[0]}"

                }
            }
        }
        stage ('Deploy to Tomcat') {

            steps {
                // sh 'curl user_name:passowrd source destination'
               // sh 'curl -v -u saacademy:password -T /var/lib/jenkins/workspace/Jenkins_Project/target/spring3-mvc-maven-xml-hello-world-1.0-SNAPSHOT.war http://34.145.55.59:8080/manager/text/deploy?path=/spring-cloud'
                sh 'curl -v -u ${TOMCAT_CREDS_USER}:${TOMCAT_CREDS_PSW} -T /var/lib/jenkins/workspace/Jenkins_Project/target/spring3-mvc-maven-xml-hello-world-1.0-SNAPSHOT.war http://34.145.55.59:8080/manager/text/deploy?path=/spring-cloud '
            }
        }
    }
}

