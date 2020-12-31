package com.github.bazpiyo.itdddkt.webapplication.handler

import com.github.bazpiyo.itdddkt.application.circles.CircleApplicationService
import com.github.bazpiyo.itdddkt.application.circles.ICircleQueryService
import com.github.bazpiyo.itdddkt.application.circles.commons.CircleData
import com.github.bazpiyo.itdddkt.application.circles.create.CircleCreateCommand
import com.github.bazpiyo.itdddkt.application.circles.delete.CircleDeleteCommand
import com.github.bazpiyo.itdddkt.application.circles.get.CircleGetCommand
import com.github.bazpiyo.itdddkt.application.circles.getcirclemembers.CircleGetMembersCommand
import com.github.bazpiyo.itdddkt.application.circles.getcirclemembers.CircleGetMembersResult
import com.github.bazpiyo.itdddkt.application.circles.join.CircleJoinCommand
import com.github.bazpiyo.itdddkt.application.circles.leave.CircleLeaveCommand
import com.github.bazpiyo.itdddkt.application.circles.update.CircleUpdateCommand
import com.github.bazpiyo.itdddkt.webapplication.ApiEndpointConfig
import com.github.bazpiyo.itdddkt.webapplication.models.circles.circlemembers.CircleMemberResponseModel
import com.github.bazpiyo.itdddkt.webapplication.models.circles.circlemembers.CircleMembersResponseModel
import com.github.bazpiyo.itdddkt.webapplication.models.circles.circlemembers.MemberResponseModel
import com.github.bazpiyo.itdddkt.webapplication.models.circles.commons.CircleResponseModel
import com.github.bazpiyo.itdddkt.webapplication.models.circles.get.CircleGetResponseModel
import com.github.bazpiyo.itdddkt.webapplication.models.circles.index.CircleIndexResponseModel
import com.github.bazpiyo.itdddkt.webapplication.models.circles.joinmember.CircleJoinMemberRequestModel
import com.github.bazpiyo.itdddkt.webapplication.models.circles.leavemember.CircleLeaveMemberRequestModel
import com.github.bazpiyo.itdddkt.webapplication.models.circles.post.CirclePostRequestModel
import com.github.bazpiyo.itdddkt.webapplication.models.circles.post.CirclePostResponseModel
import com.github.bazpiyo.itdddkt.webapplication.models.circles.put.CirclePutRequestModel
import org.springframework.stereotype.Component
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse

@Component
class CircleHandler(
  private val circleApplicationService: CircleApplicationService,
  private val circleQueryService: ICircleQueryService,
  private val apiEndpointConfig: ApiEndpointConfig
) : HandlerTrait {
  fun index(request: ServerRequest): ServerResponse =
    okJsonResponse {
      circleApplicationService
        .getAll()
        .toCircleResponseModelList()
        .let(CircleIndexResponseModel.Companion::of)
    }

  fun get(request: ServerRequest): ServerResponse =
    okJsonResponse {
      request.pathVariable("id")
        .let(CircleGetCommand.Companion::of)
        .let(circleApplicationService::get)
        .circle.toCircleResponseModel()
        .let(CircleGetResponseModel.Companion::of)
    }

  fun circleMembers(request: ServerRequest): ServerResponse =
    okJsonResponse {
      val page = request.param("page").orElseGet { CircleGetMembersCommand.defaultPage.toString() }
      val limit = request.param("limit").orElseGet { CircleGetMembersCommand.defaultPageLimit.toString() }
      val command = CircleGetMembersCommand.of(page, limit)
      circleQueryService.getCircleMembers(command)
        .toListCircleMemberResponseModel()
        .let(CircleMembersResponseModel.Companion::of)
    }

  fun post(request: ServerRequest): ServerResponse =
    createResponse {
      val circlePostRequestModel = request.body(CirclePostRequestModel::class.java)
      val command = CircleCreateCommand.of(
        circlePostRequestModel.circleName,
        circlePostRequestModel.ownerId
      )
      val result = circleApplicationService.create(command)
      val path = apiEndpointConfig.circlePath()
      CirclePostResponseModel(result.createdCircleId).toURI(path)
    }

  fun put(request: ServerRequest): ServerResponse =
    updateResponse {
      val id = request.pathVariable("id")
      val circlePutRequestModel = request.body(CirclePutRequestModel::class.java)
      val command = CircleUpdateCommand.of(
        ownerId = circlePutRequestModel.circleName,
        name = id
      )
      circleApplicationService.update(command)
    }

  fun joinMember(serverRequest: ServerRequest): ServerResponse = updateResponse {
    val circleId = serverRequest.pathVariable("id")
    val circleJoinMemberRequestModel = serverRequest.body(CircleJoinMemberRequestModel::class.java)
    val command = CircleJoinCommand.of(
      memberId = circleJoinMemberRequestModel.userId,
      circleId = circleId
    )
    circleApplicationService.join(command)
  }

  fun leaveMember(serverRequest: ServerRequest): ServerResponse = updateResponse {
    val circleId = serverRequest.pathVariable("id")
    val circleJoinMemberRequestModel = serverRequest.body(CircleLeaveMemberRequestModel::class.java)
    val command = CircleLeaveCommand.of(
      memberId = circleJoinMemberRequestModel.userId,
      circleId = circleId
    )
    circleApplicationService.leave(command)
  }

  fun delete(request: ServerRequest): ServerResponse =
    deleteResponse {
      val id = request.pathVariable("id")
      val command = CircleDeleteCommand.of(id)
      circleApplicationService.delete(command)
    }

  private fun List<CircleData>.toCircleResponseModelList(): List<CircleResponseModel> =
    map { it.toCircleResponseModel() }

  private fun CircleData.toCircleResponseModel(): CircleResponseModel =
    CircleResponseModel.of(
      id = id,
      name = name,
      ownerUrl = apiEndpointConfig.userApiUrlId(ownerId),
      memberUrls = apiEndpointConfig.userApiUrlIds { memberIds }
    )

  private fun CircleGetMembersResult.toListCircleMemberResponseModel(): List<CircleMemberResponseModel> =
    map {
      CircleMemberResponseModel(
        circleId = it.circleId,
        circleName = it.circleName,
        ownerId = it.ownerId,
        ownerName = it.ownerName,
        ownerType = it.ownerType,
        members = it.members.map { m ->
          MemberResponseModel(
            memberId = m.memberId,
            memberName = m.memberName,
            memberType = m.memberType
          )
        }
      )
    }

}