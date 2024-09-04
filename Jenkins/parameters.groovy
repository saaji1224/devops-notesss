// this is for parameter example
pipeline {
    agent any
    parameters {
        // string,text,booleanParam,choice,password
        string (name: 'PERSON', defaultValue: 'Sajith', description: 'who should i say hello to?')
        string (name: 'BRANCH_NAME', defaultValue: 'main', description: 'whats the branch i should build?')
        booleanParam (
            // true,false
            name: 'TOOGLE',
            defaultValue: true,
            description: 'toogle this value'
        )
        choice (
            name: 'ENV',
            choices: ['dev', 'test', 'stage', 'prod'],
            description: 'select the env you want to deply'
        )
    }
    stages {
        stage ('example') {
            steps {
                echo "Heloo ${params.PERSON}"
                echo "boolean parameter is: ${params.TOOGLE}"
                echo "deploying to ${params.ENV} environment"
            }
        }
    }
}