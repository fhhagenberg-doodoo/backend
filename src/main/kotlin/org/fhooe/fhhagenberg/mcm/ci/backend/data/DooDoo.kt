package org.fhooe.fhhagenberg.mcm.ci.backend.data

import java.sql.Timestamp
import java.util.*

class DooDoo(
        val name: String,
        val description: String,
        val dueDate: Date,
        val doneSince: Date?,
        val priority: Int
) {
    var isDone: Boolean = false
    get() = null != this.doneSince
}