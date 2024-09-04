// This environment block can be used at pipeline level and stage level
pipeline {
    agent any
    environment {
        // these environment variable is used to across all the stage
        //key value pairs
        name = "sajith"
        course = "k8s"
    }
    stages {
        stage ("build") {

            // these environment variables are specific to this stage only
            environment {
                cloud = "GCP"
            }

            steps {
                echo "welocme to ${name}"
                echo "you enrolled to ${course} Course"
                echo "you are certified in ${cloud}"
            }
            
        }
        stage("second") {
            steps {
                echo "welocme to ${name}"
                echo "you enrolled to ${course} Course"
                echo "you are certified in ${cloud}" // this got faild beacuse these variable is not defined in these stage.

            }
        }
    }
}

// Lets test presedence
pipeline {
    agent any
    environment {
        name = "sajith"
        course = "k8s"
    }
    stages {
        stage ("build"){
            environment {
                cloud = "GCP"
                name = "saaji"
            }
            steps {
                echo "welocme to ${name}"
                echo "you enrolled to ${course} Course"
                echo "you are certified in ${cloud}"
                // what we define in stage level that will be taken into consideration fisrt comparative same variable is been availanle in the pipeline level 
                sh "printenv" 
            }
        }
    }
}