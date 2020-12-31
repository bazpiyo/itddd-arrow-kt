package com.github.bazpiyo.itdddkt.infrastructure.queryservice

import com.github.bazpiyo.itdddkt.application.circles.ICircleQueryService
import com.github.bazpiyo.itdddkt.application.circles.getcirclemembers.CircleGetMembersCommand
import com.github.bazpiyo.itdddkt.application.circles.getcirclemembers.CircleGetMembersResult
import com.github.bazpiyo.itdddkt.application.circles.getcirclemembers.CircleMemberData
import com.github.bazpiyo.itdddkt.application.circles.getcirclemembers.MemberData
import org.springframework.stereotype.Repository

@Repository
class CircleQueryService(private val mapper: CircleQueryMapper) : ICircleQueryService {
  override fun getCircleMembers(command: CircleGetMembersCommand): CircleGetMembersResult =
    mapper.getCircleMembers(
      limit = command.limit,
      offset = command.offset
    ).map {
      CircleMemberData(
        circleId = it.circleId,
        circleName = it.circleName,
        ownerId = it.ownerId,
        ownerName = it.ownerName,
        ownerType = it.ownerType,
        members = it.members.map { m ->
          MemberData(
            memberId = m.memberId,
            memberName = m.memberName,
            memberType = m.memberType
          )
        }
      )
    }.let { CircleGetMembersResult(it) }
}