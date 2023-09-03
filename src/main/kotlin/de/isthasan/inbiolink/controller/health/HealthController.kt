package de.isthasan.inbiolink.controller.health

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RequestMapping("/health")
@RestController
@Tag(name = "Health")
class HealthController {
    @Value("\${app.version}")
    private val version: String = ""

    @Value("\${server.port}")
    private val port: String = ""


    @GetMapping
    fun isAlive(): String {
        return "Hey, InBioLink is running on port $port with version: $version"
    }
}