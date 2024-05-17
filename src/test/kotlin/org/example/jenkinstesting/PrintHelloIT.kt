package org.example.jenkinstesting

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@SpringBootTest
@AutoConfigureWebTestClient
class PrintHelloIT(
    private val webTestClient: WebTestClient
) : BehaviorSpec({
    val endPoint = "/api/hello"
    val request = webTestClient.get().uri(endPoint)

    Given("Hello world 요청") {
        When("정상 요청") {
            Then("Response 200 - Hello world") {
                request
                    .exchange()
                    .expectStatus().isOk
                    .expectBody<String>()
                    .returnResult()
                    .responseBody!!
                    .should {
                        it.shouldBeTypeOf<String>()
                        it shouldBe "Hello World"
                    }
            }
        }
    }
})