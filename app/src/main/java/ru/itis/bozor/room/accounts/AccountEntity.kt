package ru.itis.bozor.room.accounts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "accounts",
    indices = [
        Index("email", unique = true)
    ]
)
data class AccountEntity(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "email", collate = ColumnInfo.NOCASE) val email: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "password") val password: String,
) {

    fun toAccount(): Account = Account(
        id = id,
        username = username,
        email = email,
    )

    companion object {
        fun fromSignUpData(signUpData: SignUpData): AccountEntity = AccountEntity(
            id = 0,
            username = signUpData.username,
            email = signUpData.email,
            password = signUpData.password,
        )
    }

}