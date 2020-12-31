package com.github.bazpiyo.itdddkt.common.exception.application

data class NotMatchTypeUserIdException(private val userId: String) : RuntimeException() {
  override val message: String =
    "不正なユーザIDが指定されました。ユーザIDは数値で指定してください。UserId=$userId"
}

data class NotFoundUserException(private val userId: Int) : RuntimeException() {
  override val message: String =
    "存在しないユーザIDが指定されました。UserId=${userId}"
}

data class CanNotRegisterUserException(private val userName: String) : RuntimeException() {
  override val message: String =
    "指定ユーザ名は既に存在しています。 UserName=${userName}"
}

