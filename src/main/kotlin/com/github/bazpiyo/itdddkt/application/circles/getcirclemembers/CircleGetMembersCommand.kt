package com.github.bazpiyo.itdddkt.application.circles.getcirclemembers

data class CircleGetMembersCommand(
  val limit: Int,
  val offset: Int
) {
  companion object {
    const val defaultPage = 1
    const val defaultPageLimit = 100
    fun of(page: Int, limit: Int): CircleGetMembersCommand {
      val offset = (page - 1) * limit
      return CircleGetMembersCommand(limit, offset)
    }

    fun of(page: String, limit: String): CircleGetMembersCommand =
      page.toIntOrNull()?.let { p ->
        limit.toIntOrNull()?.let { l ->
          of(p, l)
        }
      } ?: throw RuntimeException("")


  }
}