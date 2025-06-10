// pipeline {
//     agent any

//     environment {
//         PROJECT_NAME = "rizkirahmann-dev"
//         BUILD_NAME = "java-bni-project-git"
//     }

//     stages {
//         stage('Trigger Build in Openshift') {
//             steps {
//                 sh "oc start-build ${BUILD_NAME} --from-dir=. --follow -n ${PROJECT_NAME}"
//             }
//         }

//         stage('Deploy to Openshift') {
//             steps {
//                 sh "oc rollout restart deployment/${BUILD_NAME} -n ${PROJECT_NAME}"
//             }
//         }
//     }

//     post{
//         success {
//             echo '✅ Build & deploy berhasil via Openshift BuildConfig.'
//         }
//         failure {
//             echo '❌ Gagal menjalankan pipeline!'
//         }
//     }
// }


pipeline {
    agent any

    environment {
        PROJECT_NAME = "rizkirahmann-dev"
        BUILD_NAME = "java-bni-project-git"
    }

    stages {
        stage('Trigger Build in OpenShift') {
            steps {
                // Ini akan trigger Git-based BuildConfig
                sh "oc start-build ${BUILD_NAME} --follow -n ${PROJECT_NAME}"
            }
        }

        stage('Deploy to OpenShift') {
            steps {
                sh "oc rollout restart deployment/${BUILD_NAME} -n ${PROJECT_NAME}"
            }
        }
    }

    post {
        success {
            echo '✅ Build & deploy berhasil via OpenShift BuildConfig (source dari Git).'
        }
        failure {
            echo '❌ Gagal menjalankan pipeline!'
        }
    }
}
