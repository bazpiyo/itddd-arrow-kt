package com.github.bazpiyo.itdddkt.webapplication.handler

import com.github.bazpiyo.itdddkt.common.exception.application.*
import com.github.bazpiyo.itdddkt.common.exception.domain.*
import com.github.bazpiyo.itdddkt.common.exception.infrastructure.CircleTableException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.servlet.function.ServerResponse
import java.net.URI

interface HandlerTrait {
  val log: Logger
    get() = LoggerFactory.getLogger(this::class.java)

  fun okJsonResponse(f: () -> Any): ServerResponse = kotlin.runCatching {
    ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(f())
  }.getOrElse(this::errorMatcher)

  fun deleteResponse(f: () -> Unit): ServerResponse =
    kotlin.runCatching {
      f()
      ServerResponse.noContent().build()
    }.getOrElse(this::errorMatcher)

  fun createResponse(f: () -> URI): ServerResponse =
    kotlin.runCatching {
      ServerResponse.created(f()).build()
    }.getOrElse(this::errorMatcher)

  fun updateResponse(f: () -> Unit): ServerResponse =
    kotlin.runCatching {
      f()
      ServerResponse.noContent().build()
    }.getOrElse(this::errorMatcher)

  fun errorMatcher(e: Throwable): ServerResponse {
    log.error(e.message, e)
    return when (e) {
      is NotMatchTypeUserIdException -> HttpStatus.BAD_REQUEST to e.message
      is NotFoundUserException -> HttpStatus.NOT_FOUND to e.message
      is CanNotRegisterUserException -> HttpStatus.CONFLICT to e.message
      is ArgumentCircleNameException -> HttpStatus.BAD_REQUEST to e.message
      is CircleFullException -> HttpStatus.BAD_REQUEST to e.message
      is NotMatchUserTypeException -> HttpStatus.NOT_FOUND to e.message
      is ArgumentUserNameException -> HttpStatus.BAD_REQUEST to e.message
      is NotFoundCirclesException -> HttpStatus.NOT_FOUND to e.message
      is NotMatchTypeCircleIdException -> HttpStatus.BAD_REQUEST to e.message
      is CanNotRegisterCircleException -> HttpStatus.BAD_REQUEST to e.message
      is CircleTableException -> HttpStatus.INTERNAL_SERVER_ERROR to e.message
      is CircleJoinException -> HttpStatus.BAD_REQUEST to e.message
      is CircleLeaveException -> HttpStatus.BAD_REQUEST to e.message
      else -> HttpStatus.INTERNAL_SERVER_ERROR to "管理者に連絡してください"
    }.let {
      val error: (String) -> Map<String, String> = { es -> mapOf("error" to es) }
      ServerResponse.status(it.first).contentType(MediaType.APPLICATION_JSON).body(error(it.second))
    }
  }
}