package org.fhooe.fhhagenberg.mcm.ci.backend

import java.time.OffsetDateTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import lombok.extern.slf4j.Slf4j
import org.fhooe.fhhagenberg.mcm.ci.backend.data.DooDoo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@Slf4j
class DooDooService {

    @Autowired
    private lateinit var repository: DooDooRepository

    suspend fun findAll(): Flow<DooDoo> {
        return repository.findAll().asFlow()
    }

    suspend fun create(doodoo: DooDoo): DooDoo? {
        return repository.save(doodoo).awaitSingle()
    }

    suspend fun delete(id: String): String? {
        return if (repository.findById(id).awaitFirstOrNull() == null) {
            null
        } else {
            repository.deleteById(id).awaitFirstOrNull()
            id
        }
    }

    suspend fun update(doodoo: DooDoo): DooDoo? {
        val existingObject = doodoo.id?.let { id ->
            repository.findById(id).awaitFirstOrNull()
        }

        return if (null == existingObject) {
            null
        } else {
            repository.save(doodoo).awaitSingle()
        }
    }

    suspend fun findBy(id: String): DooDoo? {
        return repository.findById(id).awaitFirstOrNull()
    }

    suspend fun setDone(id: String): DooDoo? {
        val result = repository.findById(id).awaitSingle()
        result.doneSince = OffsetDateTime.now()
        return repository.save(result).awaitSingle()
    }
}
