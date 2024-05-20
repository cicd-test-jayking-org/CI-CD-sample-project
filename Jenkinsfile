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
                    // Docker 이미지 빌드
                    sh 'docker build -t jenkins-testing:latest .'
                }
            }
        }
        stage('Login to AWS ECR') {
            steps {
                script {
                    // AWS ECR 로그인
                    sh '''
                    aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com
                    '''
                }
            }
        }
        stage('Tag & Push to ECR') {
            steps {
                script {
                    // Docker 이미지 태그 및 ECR로 푸시
                    sh '''
                    docker tag jenkins-testing:latest $DOCKER_IMAGE:latest
                    docker push $DOCKER_IMAGE:latest
                    '''
                }
            }
        }
    }
}