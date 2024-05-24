package org.example.jenkinstesting

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JenkinsTestingApplication

fun main(args: Array<String>) {
    println("test line1")
    println("test line2")
    runApplication<JenkinsTestingApplication>(*args)
}
