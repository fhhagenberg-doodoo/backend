package org.fhooe.fhhagenberg.mcm.ci.backend

import org.fhooe.fhhagenberg.mcm.ci.backend.data.DooDoo
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DooDooTest {

    @Test
    fun `isDone-property returning false`() {
        val doodoo = DooDoo("id", "test1", "test2", Date(), null, 1)
        assertFalse(doodoo.isDone)
    }

    @Test
    fun `isDone-property returning true`() {
        val doodoo = DooDoo("id", "test1", "test2", Date(), Date(), 3)
        assertTrue(doodoo.isDone)
    }
}