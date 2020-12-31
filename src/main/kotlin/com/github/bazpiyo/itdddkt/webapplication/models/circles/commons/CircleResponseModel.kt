package com.github.bazpiyo.itdddkt.webapplication.models.circles.commons

data class CircleResponseModel(
  val id: Int,
  val name: String,
  val ownerUrl: String,
  val memberUrls: List<String>
) {
  companion object {
    fun of(id: Int, name: String, ownerUrl: String, memberUrls: List<String>): CircleResponseModel =
      CircleResponseModel(
        id = id,
        name = name,
        ownerUrl = ownerUrl,
        memberUrls = memberUrls
      )
  }
}