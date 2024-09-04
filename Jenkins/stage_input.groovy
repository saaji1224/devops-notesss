pipeline {
    agent any 
    stages {
        stage ('Stages running parallel') {
            failFast true
            parallel {
                stage ('sonarscan') {

                    steps {
                        echo "executing  sonar scan"
                        sleep 10
                    }
                }
                stage ('fortifyscan') {
                    steps {
                        echo "executing fortify scan"
                        sleep 10
                        //error "simulating error during fortify"
                    }
                }
                stage ('checkmark scan') {
                    steps {
                        echo "executing checkmar scan"
                        sleep 10 
                    }
                }
            }

        }
        stage ('deply to dev') {
            steps {
                echo "deploying to dev"
                sleep 10
            }
        }
        stage ('deploy to qa'){
            steps {
                echo "deploying to qa"
                sleep 10
            }
        }
    }
}

// input
pipeline {
    agent any
    stages {
        stage ('build') {
            steps {
                timeout (time: 300, unit:'SECONDS') {
                    input message: 'are you building the application', ok: 'yes', submitter: 'linux'
                }
                echo "building the application"
            }
        }
    }
}