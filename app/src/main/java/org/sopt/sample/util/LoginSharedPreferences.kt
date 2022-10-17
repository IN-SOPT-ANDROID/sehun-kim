package org.sopt.sample.util

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import org.sopt.sample.data.local.LoginInfo

class LoginSharedPreferences(context: Context) {
    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val sharedPreferences = EncryptedSharedPreferences.create(
        "encryptedShared",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveLoginInfo(info: LoginInfo) {
        sharedPreferences.edit().run {
            putString(LOGIN_ID, info.id)
            putString(LOGIN_PW, info.pw)
        }
    }

    companion object {
        private const val LOGIN_ID = "loginid"
        private const val LOGIN_PW = "loginpw"
    }
}
