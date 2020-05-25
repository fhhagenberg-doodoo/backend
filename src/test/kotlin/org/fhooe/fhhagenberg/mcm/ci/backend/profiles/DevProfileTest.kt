package org.fhooe.fhhagenberg.mcm.ci.backend.profiles

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@ActiveProfiles("dev")
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DevProfileTest {

    @Autowired
    private val webTestClient: WebTestClient? = null

    @Test
    fun `swagger available`() {
        webTestClient!!
                .get()
                .uri("/swagger-ui.html")
                .exchange()
                .expectStatus()
                .isOk
    }
}