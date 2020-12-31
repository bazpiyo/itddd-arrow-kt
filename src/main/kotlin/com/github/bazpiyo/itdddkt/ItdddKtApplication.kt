package com.github.bazpiyo.itdddkt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class ItdddKtApplication

fun main(args: Array<String>) {
  runApplication<ItdddKtApplication>(*args)
}
