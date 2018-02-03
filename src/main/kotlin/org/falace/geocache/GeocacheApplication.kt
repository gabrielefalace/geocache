package org.falace.geocache

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GeocacheApplication

fun main(args: Array<String>) {
    runApplication<GeocacheApplication>(*args)
}
