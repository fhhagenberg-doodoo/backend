package org.fhooe.fhhagenberg.mcm.ci.backend

import org.fhooe.fhhagenberg.mcm.ci.backend.data.DooDoo
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface DooDooRepository : ReactiveCrudRepository<DooDoo, String> {

    @Transactional
    @Query("SELECT id, name, description, due_date, done_since, priority FROM doodoo")
    override fun findAll(): Flux<DooDoo>

    @Transactional
    @Query("SELECT id, name, description, due_date, done_since, priority FROM doodoo WHERE id=$1")
    override fun findById(id: String): Mono<DooDoo>

    @Transactional
    override fun <S : DooDoo?> save(entity: S): Mono<S>

    @Transactional
    @Query("DELETE FROM doodoo WHERE id=$1")
    override fun deleteById(id: String): Mono<Void>
}
