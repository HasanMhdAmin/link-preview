package de.isthasan.inbiolink.controller.proxy

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate


@CrossOrigin
@RequestMapping("/proxy")
@RestController
@Tag(name = "Proxy")
class ProxyController {

    @GetMapping
    fun proxyRequest(@RequestParam url: String): ResponseEntity<String> {
        return try {
            // Create a RestTemplate instance to make the HTTP request
            val restTemplate = RestTemplate()

            // Make the GET request to the external URL
            // Return the response received from the external URL
            restTemplate.getForEntity(url, String::class.java)
        } catch (e: Exception) {
            // Handle exceptions as needed (e.g., log the error)
            e.printStackTrace()
            ResponseEntity.status(500).body("Error: " + e.message)
        }
    }

}