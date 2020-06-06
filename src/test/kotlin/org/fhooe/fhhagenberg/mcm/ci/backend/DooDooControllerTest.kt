package org.fhooe.fhhagenberg.mcm.ci.backend

import org.assertj.core.api.Assertions.assertThat
import org.fhooe.fhhagenberg.mcm.ci.backend.data.DooDoo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Stream

@Transactional
@AutoConfigureWebTestClient
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DooDooControllerTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Nested
    inner class GetRequests {

        @Test
        fun `Get all objects`() {
            webTestClient
                .get()
                .uri("/doodoos")
                .exchange()
                .expectStatus()
                .isOk
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody<Array<DooDoo>>()
                .consumeWith { doodoos ->
                    assertEquals(2, doodoos.responseBody!!.size)
                    assertTrue(doodoos.responseBody!!.asList().stream()
                        .anyMatch { it.name == "test" })
                    assertTrue(doodoos.responseBody!!.asList().stream()
                        .anyMatch { it.name == "test2" })
                }
        }

        @Test
        fun `Get object by id`() {
            val doodoos = webTestClient
                .get()
                .uri("/doodoos")
                .exchange()
                .expectStatus().isOk
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody<Array<DooDoo>>()
                .returnResult().responseBody!!

            val doodoo = doodoos.first()
            val fetchedDoodoo = webTestClient
                .get()
                .uri("/doodoos/${doodoo.id!!}")
                .exchange()
                .expectStatus().isOk
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(DooDoo::class.java)
                .returnResult().responseBody!!

            assertEquals(doodoo.id, fetchedDoodoo.id)
        }

        @Test
        fun `Get non-existent object by id`() {
            webTestClient
                .get()
                .uri("/doodoos/does-not-exist")
                .exchange()
                .expectStatus().isNotFound
                .expectBody().isEmpty
        }
    }

    @Nested
    inner class CreateRequest {

        @Test
        fun `Create valid object`() {
            val result = webTestClient
                .post()
                .uri("/doodoos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""{
  "name": "test3",
  "description": "test-description",
  "dueDate": "2020-10-12T08:08:12.000+02:00",
  "doneSince": null,
  "priority": 4
}""")
                .exchange()
                .expectStatus().isCreated
                .returnResult(DooDoo::class.java)
                .responseBody.blockFirst()!!
                .id?.isNotEmpty()!!

            assertTrue(result)
        }

   /*     @Test
        fun `Create invalid object`() {
            val result = webTestClient
                .post()
                .uri("/doodoos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""{
  "name": "test3",
  "description": "test-description",
  "dueDate": "2020-10-12T08:08:12.000+02:00",
  "doneSince": null,
  "priority": 6
}""")
                .exchange()
                .expectStatus().isBadRequest
                .returnResult(DooDoo::class.java)
                .responseBody.blockFirst()!!

            assertEquals("""""", result)
        }*/
    }

    @Nested
    inner class UpdateRequest {

        @Test
        fun `Update existing object`() {
            webTestClient
                .get()
                .uri("/doodoos")
                .exchange()
                .expectStatus()
                .isOk
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody<Array<DooDoo>>()
                .consumeWith { doodoos ->
                    assertEquals(3, doodoos.responseBody!!.size)
                    val doodoo = doodoos.responseBody!![0]

                    val updatedDoodoo = DooDoo(
                        doodoo.id,
                        doodoo.name,
                        "updated-description",
                        doodoo.dueDate,
                        doodoo.doneSince,
                        doodoo.priority
                    )
                    val updatedDesc = webTestClient.put().uri("/doodoos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(updatedDoodoo)
                        .exchange()
                        .expectStatus().isOk
                        .expectHeader().contentType(MediaType.APPLICATION_JSON)
                        .expectBody(DooDoo::class.java).returnResult().responseBody!!.description
                    assertEquals("updated-description", updatedDesc)
                }
        }

        @Test
        fun `Update non-existing object`() {
            webTestClient
                .get()
                .uri("/doodoos")
                .exchange()
                .expectStatus()
                .isOk
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody<Array<DooDoo>>()
                .consumeWith { doodoos ->
                    assertEquals(3, doodoos.responseBody!!.size)
                    val doodoo = doodoos.responseBody!![0]

                    val updatedDoodoo = DooDoo(
                        "non-exisiting-id",
                        doodoo.name,
                        "updated-description",
                        doodoo.dueDate,
                        doodoo.doneSince,
                        doodoo.priority
                    )
                    webTestClient.put().uri("/doodoos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(updatedDoodoo)
                        .exchange()
                        .expectStatus().isOk
                        .expectBody().isEmpty
                }
        }

        @Nested
        inner class DeleteRequest {
            @Test
            fun `Delete existing object`() {
                val doodoo = webTestClient
                    .get()
                    .uri("/doodoos")
                    .exchange()
                    .expectStatus().isOk
                    .expectHeader().contentType(MediaType.APPLICATION_JSON)
                    .expectBody<Array<DooDoo>>()
                    .returnResult().responseBody!![0]

                val result = webTestClient.delete().uri("/doodoos/${doodoo.id}")
                    .exchange()
                    .expectStatus().isOk
                    .expectBody(String::class.java).returnResult().responseBody!!

                assertEquals(doodoo.id, result)

                val size = webTestClient
                    .get()
                    .uri("/doodoos")
                    .exchange()
                    .expectStatus().isOk
                    .expectHeader().contentType(MediaType.APPLICATION_JSON)
                    .expectBody<Array<DooDoo>>()
                    .returnResult().responseBody!!.size

                assertEquals(2, size)
            }

            @Test
            fun `Delete non-existing object`() {
                webTestClient.delete().uri("/doodoos/non-existing-id")
                    .exchange()
                    .expectStatus().isOk
                    .expectBody().isEmpty

                val size = webTestClient
                    .get()
                    .uri("/doodoos")
                    .exchange()
                    .expectStatus().isOk
                    .expectHeader().contentType(MediaType.APPLICATION_JSON)
                    .expectBody<Array<DooDoo>>()
                    .returnResult().responseBody!!.size

                assertEquals(2, size)
            }
        }
    }

    @Nested
    inner class SetDoneRequest {

        @Test
        fun `Set done existing object`() {
            val doodoo = webTestClient
                .get()
                .uri("/doodoos")
                .exchange()
                .expectStatus().isOk
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody<Array<DooDoo>>()
                .returnResult().responseBody!!.filter { it.doneSince == null }.first()

            assertNull(doodoo.doneSince)

            val doneDoodoo = webTestClient.put().uri("/doodoos/${doodoo.id}/done")
                .exchange()
                .expectStatus().isOk
                .expectBody(DooDoo::class.java).returnResult().responseBody!!

            assertNotNull(doneDoodoo.doneSince)
            assertEquals(doodoo.id, doneDoodoo.id)
        }

        @Test
        fun `Set done non-existing object`() {
            webTestClient.put().uri("/doodoos/non-exisiting-id/done")
                .exchange()
                .expectStatus().isOk
                .expectBody().isEmpty
        }
    }
}
