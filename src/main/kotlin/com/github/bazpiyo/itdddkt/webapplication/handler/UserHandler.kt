package com.github.bazpiyo.itdddkt.webapplication.handler

import com.github.bazpiyo.itdddkt.application.users.UserApplicationService
import com.github.bazpiyo.itdddkt.application.users.commons.UserData
import com.github.bazpiyo.itdddkt.application.users.delete.UserDeleteCommand
import com.github.bazpiyo.itdddkt.application.users.get.UserGetCommand
import com.github.bazpiyo.itdddkt.application.users.getall.UserGetAllResult
import com.github.bazpiyo.itdddkt.application.users.register.UserRegisterCommand
import com.github.bazpiyo.itdddkt.application.users.update.UserUpdateCommand
import com.github.bazpiyo.itdddkt.webapplication.ApiEndpointConfig
import com.github.bazpiyo.itdddkt.webapplication.models.users.commons.UserResponseModel
import com.github.bazpiyo.itdddkt.webapplication.models.users.get.UserGetResponseModel
import com.github.bazpiyo.itdddkt.webapplication.models.users.index.UserIndexResponseModel
import com.github.bazpiyo.itdddkt.webapplication.models.users.post.UserPostRequestModel
import com.github.bazpiyo.itdddkt.webapplication.models.users.post.UserPostResponseModel
import com.github.bazpiyo.itdddkt.webapplication.models.users.put.UserPutRequestModel
import org.springframework.stereotype.Component
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse

@Component
class UserHandler(
  private val userApplicationService: UserApplicationService,
  private val apiEndpointConfig: ApiEndpointConfig
) : HandlerTrait {
  fun index(serverRequest: ServerRequest): ServerResponse = okJsonResponse {
    userApplicationService
      .getAll()
      .toListUserResponseModel()
      .let(UserIndexResponseModel.Companion::of)
  }

  fun get(serverRequest: ServerRequest): ServerResponse = okJsonResponse {
    val id = serverRequest.pathVariable("id")
    userApplicationService
      .get(UserGetCommand.of(id)).user
      .toUserResponseModel()
      .let(UserGetResponseModel.Companion::of)
  }

  fun post(serverRequest: ServerRequest): ServerResponse = createResponse {
    val body = serverRequest.body(UserPostRequestModel::class.java)
    val command = UserRegisterCommand.of(body.userName)
    val result = userApplicationService.register(command)
    val basePath = apiEndpointConfig.userPath()
    UserPostResponseModel(result.createdUserId).toURI(basePath)
  }

  fun put(serverRequest: ServerRequest): ServerResponse = updateResponse {
    val id = serverRequest.pathVariable("id")
    val userPutRequestModel = serverRequest.body(UserPutRequestModel::class.java)
    val command = UserUpdateCommand.of(id, userPutRequestModel.userName)
    userApplicationService.update(command)
  }

  fun delete(serverRequest: ServerRequest): ServerResponse =
    deleteResponse {
      val id = serverRequest.pathVariable("id")
      val command = UserDeleteCommand.of(id)
      userApplicationService.delete(command)
    }

  private fun UserData.toUserResponseModel(): UserResponseModel =
    UserResponseModel(id, name, type)

  private fun UserGetAllResult.toListUserResponseModel(): List<UserResponseModel> =
    map { it.toUserResponseModel() }
}
