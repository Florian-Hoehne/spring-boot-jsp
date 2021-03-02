package de.fho.lobbyinc.webapp

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.view.JstlView

@Configuration
@ComponentScan("de.fho.lobbyinc.webapp")
class AppConfig : WebMvcConfigurer
{
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/**")
            .addResourceLocations("/")
            .setCachePeriod(0)
    }

    //@Bean // filesViewController: UrlFilenameViewController
//    fun mapping(): SimpleUrlHandlerMapping {
//        val mapper = SimpleUrlHandlerMapping()
//
//        return mapper
//    }

    @Bean
    fun jspViewResolver(): InternalResourceViewResolver
    {
        val resolver = InternalResourceViewResolver()
        resolver.setPrefix("/templates/")
        resolver.setSuffix(".jsp")
        resolver.setViewClass(JstlView::class.java)
        return resolver
    }
}
