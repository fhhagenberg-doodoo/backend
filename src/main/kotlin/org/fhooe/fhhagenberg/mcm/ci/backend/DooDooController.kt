package org.fhooe.fhhagenberg.mcm.ci.backend

import kotlinx.coroutines.coroutineScope
import org.fhooe.fhhagenberg.mcm.ci.backend.data.DooDoo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/doodoos")
class DooDooController {

    @Autowired
    private lateinit var service: DooDooService

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun all() = coroutineScope {
        return@coroutineScope ResponseEntity
                .ok()
                .body(
                        service.findAll()
                )
    }

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun find(@PathVariable("id") id: String) = coroutineScope {
        return@coroutineScope ResponseEntity
                .ok()
                .body(
                        service.findBy(id)
                )
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun update(@RequestBody DooDoo: DooDoo) = coroutineScope {
        return@coroutineScope ResponseEntity
                .ok()
                .body(
                        service.update(DooDoo)
                )
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun create(@RequestBody DooDoo: DooDoo) = coroutineScope {
        return@coroutineScope service.create(DooDoo)
    }

    @DeleteMapping("/{id}")
    suspend fun delete(@PathVariable("id") id: String) = coroutineScope {
        return@coroutineScope ResponseEntity
                .ok()
                .body(
                        service.delete(id)
                )
    }

    @PutMapping("/{id}/done")
    suspend fun setDone(@PathVariable id: String) = coroutineScope {
        return@coroutineScope ResponseEntity
                .ok()
                .body(
                        service.setDone(id)
                )
    }
}