pipeline {
    agent any

    stages {
        stage(' we dont use to add git clone because we are in thefdfdfdfdfdfd same directory cleaning the application and keep testing after') {
            steps {
			bat 'mvn clean'
            }
        }
        stage(' seconde stages stagin testing the new file again after cleaning we have for testing thgis new file again  ') {
            steps {
                bat 'mvn test'            }
        }
        stage('packaging  or Deploy the application jaring the file again') {
            steps {
                bat  'mvn package'
            }
        }
    }
}
