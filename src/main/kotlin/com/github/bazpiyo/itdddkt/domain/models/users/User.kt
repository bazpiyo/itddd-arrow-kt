package com.github.bazpiyo.itdddkt.domain.models.users

import com.github.bazpiyo.itdddkt.common.exception.domain.ArgumentUserNameException
import com.github.bazpiyo.itdddkt.common.exception.domain.NotMatchUserTypeException


data class User(
  val userId: UserId,
  val userName: UserName,
  val userType: UserType
) {
  fun userNameValue(): String = userName.value
  fun userIdValue(): Int = userId.value
  fun changeName(userName: UserName): User = this.copy(userName = userName)
  fun upgrade(): User = this.copy(userType = UserType.Premium)
  fun downgrade(): User = this.copy(userType = UserType.Normal)

  companion object {
    fun of(userId: UserId, userName: UserName, userType: UserType): User =
      User(userId, userName, userType)
  }
}

data class UserName(val value: String) {
  companion object {
    fun of(userName: String): UserName =
      userName.length.let {
        when {
          it < 3 -> throw ArgumentUserNameException("3文字以上です。UserName=${it}")
          it > 20 -> throw ArgumentUserNameException("20文字以下です。UserName=${it}")
          else -> UserName(userName)
        }
      }
  }
}

data class UserId(val value: Int) {
  companion object {
    fun of(value: Int): UserId = UserId(value)
  }
}

sealed class UserType {
  abstract val name: String
  abstract val userTypeNumber: Int

  object Normal : UserType() {
    override val name: String = "一般アカウント"
    override val userTypeNumber: Int = 0
  }

  object Premium : UserType() {
    override val name: String = "プレミアムアカウント"
    override val userTypeNumber: Int = 1
  }

  companion object {
    fun of(typeNum: Int): UserType =
      when (typeNum) {
        Normal.userTypeNumber -> Normal
        Premium.userTypeNumber -> Premium
        else -> throw NotMatchUserTypeException(typeNum)
      }
  }
}

