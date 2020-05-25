package org.fhooe.fhhagenberg.mcm.ci.backend.configurations

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.web.reactive.config.ResourceHandlerRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors.regex
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux
import java.util.concurrent.Flow

@Profile("dev")
@Configuration
@EnableSwagger2WebFlux
class SwaggerConfiguration : WebFluxConfigurer {

    @Autowired
    var buildProperties: BuildProperties? = null

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(ApiInfoBuilder()
                        .description("${buildProperties?.group}.${buildProperties?.name}")
                        .title(buildProperties?.name)
                        .version(buildProperties?.version)
                        .build())
                .genericModelSubstitutes(Mono::class.java, Flux::class.java, Flow.Publisher::class.java)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/doodoos.*"))
                .build()
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/swagger-ui.html**")
                .addResourceLocations("classpath:/META-INF/resources/")
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
    }
}