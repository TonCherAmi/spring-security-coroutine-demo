package com.example.demo

import com.example.demo.handlers.TestHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@FlowPreview
@ExperimentalCoroutinesApi
@Suppress("detekt.LongMethod", "detekt.StringLiteralDuplication")
@Configuration
class RoutingConfig(val testHandler: TestHandler) {

    @Bean
    fun routes(): RouterFunction<ServerResponse> = coRouter {
        "/api".nest {
            POST("/test", testHandler::test)
        }
    }

}
