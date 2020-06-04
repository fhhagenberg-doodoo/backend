package org.fhooe.fhhagenberg.mcm.ci.backend.data

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.OffsetDateTime
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Data
@NoArgsConstructor
@AllArgsConstructor
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
