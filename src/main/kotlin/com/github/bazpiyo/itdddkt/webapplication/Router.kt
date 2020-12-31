package com.github.bazpiyo.itdddkt.webapplication

import com.github.bazpiyo.itdddkt.webapplication.handler.CircleHandler
import com.github.bazpiyo.itdddkt.webapplication.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.servlet.function.router

@Component
class Router(
  private val userHandler: UserHandler,
  private val circleHandler: CircleHandler,
  private val endpointConfig: ApiEndpointConfig
) {
  @Bean
  fun apiRouter() = router {
    endpointConfig.api.nest {
      endpointConfig.version.nest {
        endpointConfig.users.nest {
          GET("", userHandler::index)
          GET("{id}", userHandler::get)
          POST("", userHandler::post)
          PUT("{id}", userHandler::put)
          DELETE("{id}", userHandler::delete)
        }
        endpointConfig.circles.nest {
          GET("", circleHandler::index)
          GET("circlemembers", circleHandler::circleMembers)
          GET("{id}", circleHandler::get)
          POST("", circleHandler::post)
          PUT("{id}", circleHandler::put)
          PUT("{id}/join", circleHandler::joinMember)
          PUT("{id}/leave", circleHandler::leaveMember)
          DELETE("{id}", circleHandler::delete)
        }
      }
    }
  }
}
