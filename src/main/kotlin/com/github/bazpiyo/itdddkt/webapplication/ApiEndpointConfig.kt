package com.github.bazpiyo.itdddkt.webapplication

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("api-endpoint")
data class ApiEndpointConfig(
  val host: String,
  val api: String,
  val version: String,
  val users: String,
  val circles: String
) {
  fun circlePath(): String =
    listOf(api, version, circles)
      .joinToString(separator = "/", prefix = "/")

  private fun userApiUrl(): String =
    listOf(host, api, version, users)
      .joinToString(separator = "/")

  fun userApiUrlId(id: Int): String =
    userApiUrl() + "/${id}"

  fun userApiUrlIds(f: () -> List<Int>): List<String> =
    f().map(this::userApiUrlId)

  fun userPath(): String =
    listOf(api, version, users)
      .joinToString(separator = "/", prefix = "/")
}

