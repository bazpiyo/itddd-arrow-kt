package com.github.bazpiyo.itdddkt.common.exception.application

data class NotFoundCirclesException(private val circleId: Int) : RuntimeException() {
  override val message: String =
    "存在しないサークルIDが指定されました。CircleId=${circleId}"
}

data class NotMatchTypeCircleIdException(private val circleId: String) : RuntimeException() {
  override val message: String =
    "不正なユーザIDが指定されました。ユーザIDは数値で指定してください。CircleId=$circleId"
}

data class CanNotRegisterCircleException(private val circleName: String) : RuntimeException() {
  override val message: String =
    "指定ユーザ名は既に存在しています。 UserName=${circleName}"
}