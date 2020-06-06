package org.fhooe.fhhagenberg.mcm.ci.backend

import org.fhooe.fhhagenberg.mcm.ci.backend.data.DooDoo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ExtendWith(SpringExtension::class)
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DooDooRepositoryTest {

    @Autowired
    lateinit var repository: DooDooRepository

    @Nested
    inner class ReadStatements {

        @Test
        fun `select all rows`() {
            val result = repository.findAll().collectList().block()!!
            assertEquals(2, result.count())
        }

        @ParameterizedTest
        @ValueSource(ints = [0, 1])
        fun `select existing rows`(index: Int) {
            val ids = repository.findAll().collectList().block()!!.map { it.id!! }.toTypedArray()
            val result = repository.findById(ids[index]).block()!!

            assertEquals(ids[index], result.id)
        }

        @Test
        fun `select non-existent id`() {
            val result = repository.findById("wrong-id-here").block()
            assertNull(result)
        }
    }

    @Nested
    inner class CreateUpdateStatements {

        @Test
        fun `Update property from existing object`() {
            val result = repository.findAll().collectList().block()!!.first()
            val changedResult = DooDoo(
                result.id, "changed-name",
                result.description, result.dueDate,
                result.doneSince, result.priority
            )

            var resultAfterUpdate = repository.save(changedResult).block()!!
            assertEquals("changed-name", resultAfterUpdate.name)
        }

        @Test
        fun `Update property from non-existing object`() {
            val result = repository.findAll().collectList().block()!!.first()
            val changedResult = DooDoo(null, "test3",
                result.description, result.dueDate,
                result.doneSince, result.priority
            )

            var resultAfterUpdate = repository.save(changedResult).block()!!
            assertNotNull(resultAfterUpdate.id)
            assertEquals(3, repository.findAll().collectList().block()!!.count())
        }
    }

    @Nested
    inner class DeleteStatements {

        @Test
        fun `Delete existing object`() {
            val result = repository.findAll().blockFirst()!!
            repository.deleteById(result.id!!).block()
            assertEquals(2, repository.findAll().collectList().block()!!.count())
            val nullResult = repository.findById(result.id!!).block()
            assertNull(nullResult)
        }

        @Test
        fun `Delete non-existing id`() {
            repository.deleteById("non-existing-id")
            assertEquals(2, repository.findAll().collectList().block()!!.count())
        }
    }
}
