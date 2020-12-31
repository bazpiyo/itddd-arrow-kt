package com.github.bazpiyo.itdddkt.common.exception.infrastructure

object CircleTableException : RuntimeException() {
  override val message: String =
    "サークルデータ不整合発生。管理者に連絡してください"
}
