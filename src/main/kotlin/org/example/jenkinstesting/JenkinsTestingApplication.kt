package org.example.jenkinstesting

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JenkinsTestingApplication

fun main(args: Array<String>) {
    val log = KotlinLogging.logger { }
    runApplication<JenkinsTestingApplication>(*args)
    log.info { "========= CHECk CHECK ========" }
    log.info { "========= ROLLBACK TEST ========" }
    log.info { "========= CHECk CHECK ========" }
}
