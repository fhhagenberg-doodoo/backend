package org.fhooe.fhhagenberg.mcm.ci.backend

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.withContext
import org.fhooe.fhhagenberg.mcm.ci.backend.data.DooDoo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class DooDooService {

    @Autowired
    lateinit var repository: DooDooRepository

    suspend fun findAll(): Flux<DooDoo> {
        return repository.findAll()
    }

    suspend fun create(doodoo: DooDoo): Mono<DooDoo> {
        return repository.save(doodoo)
    }

    suspend fun delete(id: String): Mono<Void> {
        return repository.deleteById(id)
    }

    suspend fun update(doodoo: DooDoo): Mono<DooDoo> {
        return repository.save(doodoo)
    }

    suspend fun findBy(id: String): Mono<DooDoo> {
        return repository.findById(id)
    }

    suspend fun setDone(id: String): Mono<DooDoo> {
        var result = withContext(Dispatchers.Default) {
            repository.findById(id)
        }.awaitFirst()
        return repository.save(result)
    }
}