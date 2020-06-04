package org.fhooe.fhhagenberg.mcm.ci.backend

import java.time.OffsetDateTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import lombok.extern.slf4j.Slf4j
import org.fhooe.fhhagenberg.mcm.ci.backend.data.DooDoo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

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

    suspend fun delete(id: String): Mono<Void> {
        return repository.deleteById(id)
    }

    suspend fun update(doodoo: DooDoo): DooDoo? {
        return repository.save(doodoo).awaitSingle()
    }

    suspend fun findBy(id: String): DooDoo? {
        return repository.findById(id).awaitSingle()
    }

    suspend fun setDone(id: String): DooDoo? {
        val result = repository.findById(id).awaitSingle()
        result.doneSince = OffsetDateTime.now()
        return repository.save(result).awaitSingle()
    }
}
