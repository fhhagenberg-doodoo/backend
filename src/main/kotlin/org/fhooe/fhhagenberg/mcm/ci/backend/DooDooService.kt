package org.fhooe.fhhagenberg.mcm.ci.backend

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import org.fhooe.fhhagenberg.mcm.ci.backend.data.DooDoo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class DooDooService {

    @Autowired
    lateinit var repository: DooDooRepository

    suspend fun findAll(): Flux<DooDoo> {
        return repository.findAll()
    }
}