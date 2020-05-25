package org.fhooe.fhhagenberg.mcm.ci.backend

import kotlinx.coroutines.flow.Flow
import org.fhooe.fhhagenberg.mcm.ci.backend.data.DooDoo
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux

@Transactional
interface DooDooRepository : ReactiveCrudRepository<DooDoo, String> {

    @Query("select id, name, description, due_date, done_since, priority from doodoo")
    override fun findAll() : Flux<DooDoo>

}