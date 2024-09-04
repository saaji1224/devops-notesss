//script allows us to write custom code in groovy
// this script block,should be available in steps
// if we are having any complex environment,we can use script under steps block 
//script uses groovy as the programming language 
pipeline {
    agent any
    stages {
        stage ("hello") {
            steps {
                echo "Hello, sajith"
            }
        }

        stage ("ScriptedSatge") {
            steps {
                script {
                    def course = "k8s" // static defination
                    if (course == "k8s") {
                        println ("thanks for enrolling")
                    }
                    else
                        println ("do enrolling")
                    sleep 10 // sh "sleep 10" this will pause the pipelines,till the time mention 
                    echo "*********script block ended **************"
                    // https://www.jenkins.io/doc/pipeline/steps/workflow-basic-steps
                }

            }
        }
    }
}


