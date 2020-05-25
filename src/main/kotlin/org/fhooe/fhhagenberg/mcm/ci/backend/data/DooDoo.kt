package org.fhooe.fhhagenberg.mcm.ci.backend.data

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.Date

@Table("doodoo")
data class DooDoo(
        @Id val id: String?,
        val name: String,
        val description: String,
        val dueDate: Date,
        var doneSince: Date?,
        val priority: Int
)