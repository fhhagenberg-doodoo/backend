package org.fhooe.fhhagenberg.mcm.ci.backend

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.reactor.asFlux
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.fhooe.fhhagenberg.mcm.ci.backend.data.DooDoo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ExtendWith(SpringExtension::class)
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DooDooServiceTest {

    @Autowired
    lateinit var service: DooDooService

    @Nested
    inner class SelectObjects {

        @Test
        fun `Select all objects`() {
            val result = runBlocking {
                service.findAll()
            }.asFlux().collectList().block()!!
            assertEquals(2, result.count())
        }

        @Test
        fun `Select one object by id`() {
            val result = runBlocking {
                service.findAll().first()
            }

            val selectedById = runBlocking {
                service.findBy(result.id!!)
            }
            assertEquals(result.id, selectedById!!.id)
        }

        @Test
        fun `Select non-existing object`() {
            val selectedById = runBlocking {
                service.findBy("non-existent-id")
            }
            assertNull(selectedById)
        }
    }

    @Nested
    inner class CreateUpdateObjects {

        @Test
        fun `Update persisted object`() {
            val result = runBlocking {
                service.findAll().first()
            }

            val changedResult = DooDoo(
                result.id, "changed-name",
                result.description, result.dueDate,
                result.doneSince, result.priority
            )

            val resultAfterUpdate = runBlocking {
                service.update(changedResult)
            }!!

            assertEquals("changed-name", resultAfterUpdate.name)
        }

        @Test
        fun `Create new object`() {
            val result = runBlocking {
                service.findAll().first()
            }

            val newObject = DooDoo(
                null, "test3",
                result.description, result.dueDate,
                result.doneSince, result.priority
            )

            val resultAfterCreate = runBlocking {
                service.create(newObject)
            }!!

            assertEquals("test3", resultAfterCreate.name)
            assertEquals(3, runBlocking {
                service.findAll().asFlux().collectList().block()!!.count()
            })
        }

        @Test
        fun `Update non-existing object`() {
            val result = runBlocking {
                service.findAll().first()
            }

            val newObject = DooDoo(
                null, "test4",
                result.description, result.dueDate,
                result.doneSince, result.priority
            )

            val resultAfterUpdate = runBlocking {
                service.update(newObject)
            }

            assertNull(resultAfterUpdate)
            assertEquals(2, runBlocking {
                service.findAll().asFlux().collectList().block()!!.count()
            })
        }
    }

    @Nested
    inner class DeleteObjects {

        @Test
        fun `Delete existing object`() {
            val result = runBlocking {
                service.findAll().first()
            }

            val resultOfDeletion = runBlocking {
                service.delete(result.id!!)
            }

            assertEquals(result.id!!, resultOfDeletion)
            assertEquals(2, runBlocking {
                service.findAll().asFlux().collectList().block()!!.count()
            })
        }

        @Test
        fun `Delete non-existing object`() {
            val resultOfDeletion = runBlocking {
                service.delete("does-not-exist")
            }

            assertNull(resultOfDeletion)
            assertEquals(2, runBlocking {
                service.findAll().asFlux().collectList().block()!!.count()
            })
        }
    }

    @Nested
    inner class SetDoneObjects {

        @Test
        fun `SetDone on existing object`() {
            val resultAll = runBlocking {
                service.findAll()
            }.asFlux().collectList().block()!!

            val undoneObject = resultAll.filter { it.doneSince == null }.get(0)
            assertTrue(null == undoneObject.doneSince)

            val resultDone = runBlocking {
                service.setDone(undoneObject.id!!)
            }!!

            assertTrue(null != resultDone.doneSince )
        }

        @Test
        fun `SetDone on done object`() {
            val resultAll = runBlocking {
                service.findAll()
            }.asFlux().collectList().block()!!

            val undoneObject = resultAll.filter { it.doneSince != null }.get(0)
            assertFalse(null == undoneObject.doneSince)

            val resultDone = runBlocking {
                service.setDone(undoneObject.id!!)
            }

            assertNull(resultDone)
        }

        @Test
        fun `SetDone non-existing object`() {
            val result = runBlocking {
                service.setDone("non-existing-id")
            }

            assertNull(result)
        }
    }
}
