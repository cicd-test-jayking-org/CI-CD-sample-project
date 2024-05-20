pipeline {
    agent any
    environment {
        AWS_ACCOUNT_ID = '767397825403'
        AWS_REGION = 'ap-northeast-2'
        IMAGE_REPO_NAME = 'jenkins-cd-test'
        DOCKER_IMAGE = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${IMAGE_REPO_NAME}"
    }
    stages {
        stage('Build') {
            steps {
                script {
                    sh 'docker build -t jenkins-testing:latest .'
                }
            }
        }
        stage('Login to AWS ECR') {
//             steps {
//                 script {
//                     sh '''
//                     aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/jenkins-cd-test
//                     '''
//                 }
//             }
            steps {
                script {
                    sh '''
                    aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin 767397825403.dkr.ecr.ap-northeast-2.amazonaws.com/jenkins-cd-test
                    '''
                }
            }
        }
        stage('Tag & Push to ECR') {
            steps {
                script {
                    sh '''
                    docker tag jenkins-testing:latest $DOCKER_IMAGE:latest
                    docker push $DOCKER_IMAGE:latest
                    '''
                }
            }
        }
    }
}