package de.isthasan.inbiolink

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SpringBootApplication
class InBioLinkBackendApplication

fun main(args: Array<String>) {
    runApplication<InBioLinkBackendApplication>(*args)
}

@Configuration
class OpenApiConfig {

    @Value("\${app.version}")
    private val version: String = ""

    @Bean
    fun customOpenAPI(): OpenAPI {
//        val securityKey = "Auth Token"
//        val securityScheme = SecurityScheme()
//        securityScheme.name = "bearerAuth"
//        securityScheme.type = SecurityScheme.Type.HTTP
//        securityScheme.scheme = "bearer"

        return OpenAPI()
//            .components(
//                Components().addSecuritySchemes(securityKey, securityScheme)
//            )
//            .addSecurityItem(SecurityRequirement().addList(securityKey))
            .info(
                Info()
                    .title("In Bio Link - Service")
                    .version(version)
                    .description("Backend Service for saving instagram post's link")
            )
    }

}