package com.example.demo.handlers

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@ExperimentalCoroutinesApi
@FlowPreview
@Component
class TestHandler {

    @PreAuthorize("hasRole('ADMIN')")
    suspend fun test(serverRequest: ServerRequest): ServerResponse {
        return serverRequest
                .awaitBody<String>()
                .let { ServerResponse.ok().bodyValueAndAwait(it) }
    }

}
