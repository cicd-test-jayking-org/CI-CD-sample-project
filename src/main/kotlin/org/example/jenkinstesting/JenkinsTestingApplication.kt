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
    log.info { "========= CHECk CHECK ========" }
    log.info { "========= 967eefc0abab3a10817c30341c28f628ff584c52 ========" }
}
