package de.fho.lobbyinc.webapp

import org.apache.catalina.Context
import org.apache.catalina.Lifecycle
import org.apache.catalina.WebResourceRoot.ResourceSetType

import java.net.MalformedURLException

import org.springframework.util.ResourceUtils

import org.apache.catalina.LifecycleEvent

import org.apache.catalina.LifecycleListener
import java.io.File
import java.net.URL


class TomcatStaticResourceLifecycle internal constructor(private val context: Context) : LifecycleListener
{
    override fun lifecycleEvent(event: LifecycleEvent)
    {
        if (event.type == Lifecycle.CONFIGURE_START_EVENT)
        {
            var location: URL = this.javaClass.protectionDomain.codeSource.location
            if (ResourceUtils.isFileURL(location)) {
                var rootFile: String = location.getFile()
                if (rootFile.endsWith("/BOOT-INF/classes/")) {
                    rootFile = rootFile.substring(0, rootFile.length - "/BOOT-INF/classes/".length + 1)
                }
                if (!File(rootFile, "META-INF" + File.separator.toString() + "resources").isDirectory) {
                    return
                }
                location = try
                {
                    File(rootFile).toURI().toURL()
                } catch (e: MalformedURLException) {
                    throw IllegalStateException("Can not add tomcat resources", e)
                }
            }
            var locationStr: String = location.toString()
            if (locationStr.endsWith("/BOOT-INF/classes!/"))
            {
                locationStr = locationStr.substring(0, locationStr.length - "/BOOT-INF/classes!/".length + 1)
                try {
                    location = URL(locationStr)
                } catch (e: MalformedURLException) {
                    throw IllegalStateException("Can not add tomcat resources", e)
                }
            }
            context.resources.createWebResourceSet(
                ResourceSetType.RESOURCE_JAR, "/", location,
                "/META-INF/resources"
            )
        }
    }
}
