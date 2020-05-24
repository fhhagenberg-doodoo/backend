package org.fhooe.fhhagenberg.mcm.ci.backend

import kotlinx.coroutines.coroutineScope
import org.fhooe.fhhagenberg.mcm.ci.backend.data.DooDoo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/doodoos")
class DooDooController {

    @GetMapping()
    suspend fun all() = coroutineScope {
        return@coroutineScope ResponseEntity.ok().body("")
    }

    @GetMapping("/{id}")
    suspend fun find(@PathVariable("id") id: String) = coroutineScope {
        return@coroutineScope ResponseEntity.ok().body(id)
    }

    @PutMapping()
    suspend fun update(@RequestBody dooDoo: DooDoo) = coroutineScope {
        return@coroutineScope ResponseEntity.ok().body(dooDoo)
    }

    @PostMapping()
    suspend fun create(@RequestBody dooDoo: DooDoo) = coroutineScope {
        val uri = URI.create("/")
        return@coroutineScope ResponseEntity.created(uri).body(dooDoo)
    }

    @DeleteMapping("/{id}")
    suspend fun delete(@PathVariable("id") id: String) = coroutineScope {

        return@coroutineScope ResponseEntity.ok().body(id)
    }

    @PutMapping("/{id}/done")
    suspend fun setDone(@PathVariable id: String) = coroutineScope {

        return@coroutineScope ResponseEntity.ok().body(id)
    }
}