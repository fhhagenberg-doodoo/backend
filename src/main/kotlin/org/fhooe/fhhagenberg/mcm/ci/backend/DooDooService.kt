package org.fhooe.fhhagenberg.mcm.ci.backend

import org.fhooe.fhhagenberg.mcm.ci.backend.data.DooDoo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DooDooService {

    @Autowired
    lateinit var repository: DooDooRepository

    suspend fun findAll() = this.repository.findAll()
}