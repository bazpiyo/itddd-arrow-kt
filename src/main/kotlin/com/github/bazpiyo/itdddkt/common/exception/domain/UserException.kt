package com.github.bazpiyo.itdddkt.common.exception.domain

data class NotMatchUserTypeException(private val userTypeNum: Int) : RuntimeException() {
  override val message: String = "存在しないユーザタイプが指定されました UserType=${userTypeNum} "
}

data class ArgumentUserNameException(private val ms: String) : RuntimeException() {
  override val message: String =
    "ユーザ名が不正です。UserName=$ms"
}
