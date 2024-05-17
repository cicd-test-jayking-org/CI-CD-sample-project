pipeline {
    agent {
        docker {
            image 'gradle:7.4.2-jdk17' // Gradle 7.4.2와 Java 17을 포함한 Docker 이미지 사용
            args '-u root -v $HOME/.gradle:/home/gradle/.gradle' // Gradle 캐시 사용
        }
    }

//     environment {
//         EC2_IP = 'your-ec2-public-ip'
//         SSH_KEY_PATH = 'path/to/your-key.pem'
//         JAR_FILE = 'build/libs/your-kotlin-app.jar'
//         DEPLOY_DIR = '/home/ec2-user/'
//     }

    stages {
//         stage('Checkout') {
//             steps {
//                 // Git 리포지토리에서 소스 코드를 체크아웃
//                 git credentialsId: 'your-credentials-id', url: 'https://github.com/your-repo.git'
//             }
//         }

        stage('Build') {
            steps {
                sh './gradlew clean build --no-daemon'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test --no-daemon'
            }
        }

//         stage('Deploy') {
//             steps {
//                 // EC2 인스턴스로 JAR 파일을 복사하고 애플리케이션을 재시작
//                 sh """
//                     scp -i ${SSH_KEY_PATH} ${JAR_FILE} ec2-user@${EC2_IP}:${DEPLOY_DIR}
//                     ssh -i ${SSH_KEY_PATH} ec2-user@${EC2_IP} << EOF
//                     pkill -f your-kotlin-app.jar
//                     nohup java -jar ${DEPLOY_DIR}/your-kotlin-app.jar > /dev/null 2>&1 &
//                     EOF
//                 """
//             }
//         }
    }

    post {
        success {
            echo 'Deployment succeeded!'
        }
        failure {
            echo 'Deployment failed!'
        }
    }
}