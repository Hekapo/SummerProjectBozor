package ru.itis.bozor.room.accounts

data class AccountSignInTuple(
    val id: Long,
    val password: String
)

data class AccountUpdateUsernameTuple(
    val id: Long,
    val username: String
)