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
            steps {
                            withCredentials([usernamePassword(credentialsId: 'jenkins-aws-cd-test', usernameVariable: 'AWS_ACCESS_KEY_ID', passwordVariable: 'AWS_SECRET_ACCESS_KEY')]) {
                script {
                    sh '''
                    aws configure set aws_access_key_id $AWS_ACCESS_KEY_ID
                    aws configure set aws_secret_access_key $AWS_SECRET_ACCESS_KEY
                    aws configure set region $AWS_REGION
                    aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/jenkins-cd-test
                    '''
                    }
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