package com.github.bazpiyo.itdddkt.application.users.register

data class UserRegisterCommand(val name: String) {
  companion object {
    fun of(name: String): UserRegisterCommand = UserRegisterCommand(name)
  }
}
