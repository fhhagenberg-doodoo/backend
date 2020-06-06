package org.fhooe.fhhagenberg.mcm.ci.backend.profiles

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@AutoConfigureWebTestClient
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProdProfileTest {
    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun `swagger not available`() {
        webTestClient
            .get()
            .uri("/swagger-ui.html")
            .exchange()
            .expectStatus()
            .isNotFound
    }
}
