package org.fhooe.fhhagenberg.mcm.ci.backend

import kotlinx.coroutines.coroutineScope
import org.fhooe.fhhagenberg.mcm.ci.backend.data.DooDoo
import org.springframework.beans.TypeMismatchException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.WebExchangeBindException
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.server.ResponseStatusException
import java.net.URI


@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/doodoos")
class DooDooController {

    @Autowired
    private lateinit var service: DooDooService

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun all() = coroutineScope {
        return@coroutineScope ResponseEntity
                .ok()
                .body(service.findAll())
    }

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun find(@PathVariable("id") id: String) = coroutineScope {

        val result = service.findBy(id)
        return@coroutineScope if (null == result) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok().body(result)
        }
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun update(@RequestBody doodoo: DooDoo) = coroutineScope {
        return@coroutineScope ResponseEntity
                .ok()
                .body(service.update(doodoo))
    }

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    suspend fun create(@RequestBody doodoo: DooDoo) = coroutineScope {
        return@coroutineScope ResponseEntity
                .created(URI("/created"))
                .body(service.create(doodoo))
    }

    @DeleteMapping("/{id}")
    suspend fun delete(@PathVariable("id") id: String) = coroutineScope {
        return@coroutineScope ResponseEntity
                .ok()
                .body(service.delete(id))
    }

    @PutMapping("/{id}/done")
    suspend fun setDone(@PathVariable id: String) = coroutineScope {
        return@coroutineScope ResponseEntity
                .ok()
                .body(service.setDone(id))
    }
}
