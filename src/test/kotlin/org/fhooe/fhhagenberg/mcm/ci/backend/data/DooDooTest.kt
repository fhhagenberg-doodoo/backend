package org.fhooe.fhhagenberg.mcm.ci.backend

import org.fhooe.fhhagenberg.mcm.ci.backend.data.DooDoo
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.time.OffsetDateTime
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DooDooTest {

    @Test
    fun `isDone-property returning false`() {
        val doodoo = DooDoo("id", "test1", "test2", OffsetDateTime.now(), null, 1)
        assertFalse(doodoo.doneSince != null)
    }

    @Test
    fun `isDone-property returning true`() {
        val doodoo = DooDoo("id", "test1", "test2", OffsetDateTime.now(), OffsetDateTime.now(), 3)
        assertTrue(doodoo.doneSince != null)
    }
}