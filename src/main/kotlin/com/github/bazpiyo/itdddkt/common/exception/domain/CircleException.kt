package com.github.bazpiyo.itdddkt.common.exception.domain

data class ArgumentCircleNameException(private val ms: String) : RuntimeException() {
  override val message: String =
    "サークル名が不正です。CircleName=$ms"
}

data class CircleFullException(private val circleId: Int) : RuntimeException() {
  override val message: String =
    "サークルに所属しているメンバーが上限に達しています。CircleId=${circleId} "
}

data class CircleJoinException(private val circleId: Int) : RuntimeException() {
  override val message: String =
    "サークルのオーナーは、メンバーに追加することができません。CircleId=${circleId}"
}

data class CircleLeaveException(private val userId: Int) : RuntimeException() {
  override val message: String =
    "指定ユーザはサークルに所属していないので解属できません。CircleId=${userId} "
}