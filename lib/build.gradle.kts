plugins {
    `java-library`
    groovy
}

repositories {
    mavenCentral()
}

configurations.all {
    resolutionStrategy {
        capabilitiesResolution.all {
            if (capability.group == "org.codehaus.groovy") {
                selectHighestVersion()
            }
        }
    }
}
dependencies {
    components.all<GroovyVersionAlign>()
    testImplementation("org.apache.groovy:groovy:4.0.6")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("org.gebish:geb-core:6.0")
}
class GroovyVersionAlign : ComponentMetadataRule {
    override fun execute(context: ComponentMetadataContext) = context.details.run {
        if (id.group.equals("org.codehaus.groovy")) {
            belongsTo("org.codehaus.groovy:groovy-bom:${id.version}", false)
        }
    }
}

tasks.named<GroovyCompile>("compileTestGroovy") {
    doFirst {
        println(classpath.files.filter {it.name.contains("groovy")}.joinToString("\n") { it.parentFile.parentFile.parentFile.parentFile.name + ":" + it.name })
    }
}
tasks.named<Test>("test") {
    useJUnitPlatform()
}

