# Spring Boot MVC Test Project
This projects uses the jstl view resolver to display java server pages.

To run the application use:

```sh
gradle bootRun
```

## JSP Handling (Deployment)
The JSTL-View resolver `InternalResoourceViewResolver` relies on the resources placed in the classpath. Both gradle and maven are able to handle an external webapp directory for internal resources. Gradle considers this directory as part of an exploded webapp like the exploded war in maven. If you want to access files from the resources by deploying a bootJar you need to add the content of the webapp folder under `META-INF/resources`. Otherwise the view resolver will not map your jsp files.

Exploded webapp
```
root
+- src
    +- main
        +- java
        +- resources
        +- webapp => (html, css, js, jsp, ...)
```

Bootable jar
```
root
+- src
    +- main
        +- java
        +- resources
            +- META-INF
                +- reources => (html, css, js, jsp, ...)
```

