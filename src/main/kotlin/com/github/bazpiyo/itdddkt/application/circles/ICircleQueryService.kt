package com.github.bazpiyo.itdddkt.application.circles

import com.github.bazpiyo.itdddkt.application.circles.getcirclemembers.CircleGetMembersCommand
import com.github.bazpiyo.itdddkt.application.circles.getcirclemembers.CircleGetMembersResult

interface ICircleQueryService {
  fun getCircleMembers(command: CircleGetMembersCommand): CircleGetMembersResult
}