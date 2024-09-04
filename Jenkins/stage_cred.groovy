pipeline {
    agent any
    environment {
        // credentials (id), this id should be the same from jenkins credentials
        Git_hub_cred = credentials ('Git_Hub')
    }
    stages {
        stage ('git-clone') {
            steps {
                echo "git hub credentials are ${Git_hub_cred}"
                echo "user id is ${Git_hub_cred_USR}"
                echo "password is ${Git_hub_cred_PSW}"
            }
        }
    }
}

//

