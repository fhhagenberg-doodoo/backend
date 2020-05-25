package org.fhooe.fhhagenberg.mcm.ci.backend

import org.fhooe.fhhagenberg.mcm.ci.backend.data.DooDoo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import java.util.Date

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DooDooControllerTest {

    @Autowired
    private val webTestClient: WebTestClient? = null

    private val doodoo = DooDoo("id", "testname", "test-description", Date(), null, 1)

    @Test
    fun `find all (get) endpoint returns 200 OK`() {
        webTestClient!!
                .get()
                .uri("/doodoos")
                .exchange()
                .expectStatus()
                .isOk
    }

    @Test
    fun `find (get) one endpoint returns 200 OK`() {
        webTestClient!!
                .get()
                .uri("/doodoos/id")
                .exchange()
                .expectStatus()
                .isOk
    }

    @Test
    fun `put (update) endpoint returns 200 OK`() {

        webTestClient!!
                .put()
                .uri("/doodoos")
                .bodyValue(doodoo)
                .exchange()
                .expectStatus()
                .isOk
    }

    @Test
    fun `delete endpoint returns 200 OK`() {
        webTestClient!!
                .delete()
                .uri("/doodoos/id")
                .exchange()
                .expectStatus()
                .isOk
    }

    @Test
    fun `post (create) endpoint returns 200 OK`() {
        webTestClient!!
                .post()
                .uri("/doodoos")
                .bodyValue(doodoo)
                .exchange()
                .expectStatus()
                .isCreated
    }

    @Test
    fun `put (set done) endpoint returns 200 OK`() {
        webTestClient!!
                .put()
                .uri("/doodoos/id/done")
                .exchange()
                .expectStatus()
                .isOk
    }
}