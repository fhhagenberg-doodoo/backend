package org.fhooe.fhhagenberg.mcm.ci.backend.data

import com.fasterxml.jackson.annotation.JsonFormat
import lombok.Data
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.sql.Timestamp
import java.time.Instant
import java.time.OffsetDateTime
import java.util.Date

@Data
@Table("doodoo")
data class DooDoo(
        @Id val id: String?,
        val name: String,
        val description: String,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        val dueDate: OffsetDateTime,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        var doneSince: OffsetDateTime?,
        val priority: Int
)