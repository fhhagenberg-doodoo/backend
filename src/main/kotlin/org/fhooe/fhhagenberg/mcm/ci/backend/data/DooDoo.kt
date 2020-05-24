package org.fhooe.fhhagenberg.mcm.ci.backend.data

import java.util.Date

class DooDoo(
        val id: String?,
        val name: String,
        val description: String,
        val dueDate: Date,
        val doneSince: Date?,
        val priority: Int
) {
    var isDone: Boolean = false
    get() = null != this.doneSince
}