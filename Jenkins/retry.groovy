// retry
pipeline {
    agent any
    stages {
        stage ("Build Stage") {
            steps {
                retry (3){
                     echo "welcome to linux"
                     error "testing the retry block" 
                }            
                echo "printing after 3 retries"   
            }
        }
    }
}

// time-out
pipeline {
    agent any
    stages {
        stage () {
            steps {
                // https://www.jenkins.io/doc/pipeline/steps/workflow-basic-steps/#timeout-enforce-time-limit
                timeout (time: 2, unit: 'SECONDS') { // Values: NANOSECONDS, MICROSECONDS, MILLISECONDS, SECONDS, MINUTES, HOURS, DAYS
                    echo "sleeping for 60 seconds"
                    sleep 60
                }
            }
        }
    }
}

// multi
pipeline {
    agent any
    stages {
        stage ('Build') {
            steps {
                echo "excuting multi-branch"
            }
        }
    }
}


//
pipeline {
    agent any 
    stages {
        stage ('build') {
            echo "hello,sajith"
        }
        stage('Scripted') {
            steps {
                script {
                    def course = "k8s" // static defination
                    if (course == "k8s") {
                        println ("thanks for enrolling to ${course}")
                    }
                    else 
                       println ("Do enroll")
                    sleep 5 //sh 'sleep 5'
                    // this will pause the pipelines till the time mentioned
                    echo "**********script block ended ****************"
                }
            }
        }
    }
}