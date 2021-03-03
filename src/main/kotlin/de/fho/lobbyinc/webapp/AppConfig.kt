package de.fho.lobbyinc.webapp

import org.apache.catalina.Context
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.view.JstlView
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory

import org.springframework.boot.web.server.WebServerFactoryCustomizer







@Configuration
@ComponentScan("de.fho.lobbyinc.webapp")
class AppConfig : WebMvcConfigurer
{
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/**")
            .addResourceLocations("/")
            .setCachePeriod(0)
    }

    @Bean
    @ConditionalOnProperty(name = ["server.static"], matchIfMissing = true)
    fun sessionManagerCustomizer(): WebServerFactoryCustomizer<TomcatServletWebServerFactory>? {
        return WebServerFactoryCustomizer { server: TomcatServletWebServerFactory ->
            server.addContextCustomizers(TomcatContextCustomizer { context: Context ->
                context.sessionTimeout = 24 * 60
                context.addLifecycleListener(TomcatStaticResourceLifecycle(context))
            })
        }
    }

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
