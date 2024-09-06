pipeline {
    agent any
    stages {
        stage ('Build'){
            steps {
                // error("this is error ")
                echo " Building the application"
            }
        }
    }
    post {
        // this will only execute if the pipeline/stage has a "Success" status
        // with blue/green in the UI
        success {
            // we can keep what ever we want here
            // typically we use mailer here
            echo "Poat activity....success is called"
        }
        // this only execute if the pipeline/stage has a "failed" status 
        failure {
            echo "post activity ....failed is called"
        }
        // always
        always {
            // mail notification is typically used here
            echo "Always block triggerd"
        }
    }
}