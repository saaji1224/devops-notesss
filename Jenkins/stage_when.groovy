// when condtion should have atleast one condition.
// https://www.jenkins.io/doc/book/pipeline/syntax/#when
pipeline {
    agent any
    environment {
        Deploy_To = 'production'
    }
    stages {
        stage ('deploy') {
            when {
                //environment name: 'Deploy_To' , value: 'production'
                //equals expected: 'production' , actual: '${Deploy_To}'
                //equals expected: 5 , actual: "${BUILD_NUMBER}"
                equals expected: 5 , actual: currentBuild.number 

            }
            steps {
                echo "deploying"
            }
        }
    }
}

// Not equal
pipeline {
    agent any
    environment {
        Deploy_To = 'production'
    }
    stages {
        stage ('deploy') {
            when {
                not {
                    equals expected: 'prod' , actual: '${Deploy_To}'
                }
                //environment name: 'Deploy_To' , value: 'production'
                equals expected: 'prod' , actual: '${Deploy_To}'
                //equals expected: 5 , actual: "${BUILD_NUMBER}"
               // equals expected: 5 , actual: currentBuild.number 

            }
            steps {
                echo "deploying"
            }
        }
    }
}

// Branch based deployment
pipeline {
    agent any
    stages {
        stage ('BUild') {
            steps {
                echo 'welcome to build'
            }
        }
        stage ('Deploy to Dev') {
            steps {
                echo "deploying to dev"
            }
        }
        stage ('Deploy to Stage') {
            when {
                expression {
                    // these stage should execute with either production branch or staging branch 
                    BRANCH_NAME ==~ /(production|staging)/
                }
            }
            steps {
                echo "deploying to stage"
            }
        }
    }
}

// allOf and anyOf
// allOf
pipeline {
    agent any
    environment {
        Deploy_To = 'production' // these is static 
    }
    stages {
        stage ('BUild') {
            steps {
                echo 'welcome to build'
            }
        }
        stage ('Deploy to Dev') {
            steps {
                echo "deploying to dev"
            }
        }
        stage ('Deploy to Stage') {
            when {
                allOf {
                    // the below all conditions should be satisfied in order for this stage that execute 
                    branch 'production'
                    environment name: 'Deploy_To' , value: 'production'
                }

            }
            steps {
                echo "deploying to stage"
            }
        }
    }
}

// anyOf
pipeline {
    agent any
    environment {
        Deploy_To = 'production' // these is static 
    }
    stages {
        stage ('BUild') {
            steps {
                echo 'welcome to build'
            }
        }
        stage ('Deploy to Dev') {
            steps {
                echo "deploying to dev"
            }
        }
        stage ('Deploy to Stage') {
            when {
                anyOf {
                    // any of the below conditions can be satisifed this stage to be executed 
                    branch 'production'
                    environment name: 'Deploy_To' , value: 'productions'
                }

            }
            steps {
                echo "deploying to stage"
            }
        }
    }
}

// i want to execute stage environment when the branch is release/
pipeline {
    agent any
    stages {
        stage ('build') {
            when {
                branch 'release-*'
            }
            steps {
                echo "deploying to stage env"
            }
        }
    }
}

// i want to execute stage environment when the branch is release-* and tag in prod only
pipeline {
    agent any
    stages {
        stage ('UAT') {
            when {
                branch 'release-*'
            }
            steps {
                echo "deploying to stage env"
            }
        }
        stage ('Prod') {
            when {
                // this stage should execute with tag only
                //buildingTag()
                // v1.2.3
                tag pattern: "v\\d{1,2}.\\d{1,2}.\\d{1,2}", comparator: "REGEXP"
            }
            steps {
                echo "deploying to prod"
            }
        }
    }
}