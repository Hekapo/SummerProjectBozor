package ru.itis.bozor.room.accounts

data class SignUpData(
    val username: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)