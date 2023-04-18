package uz.mrdos.confidentialsms.core.cache

import android.content.Context
import android.content.SharedPreferences

object AppCache {

    private val KEY_APP_CACHE_NAME = "key_app_cach"
    private val KEY_IS_PRIVATE = "key_is_private"
    private val KEY_PASSWORD = "key_password"


    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(KEY_APP_CACHE_NAME, Context.MODE_PRIVATE)
    }

    fun isPrivate() = sharedPreferences.getBoolean("$KEY_IS_PRIVATE", true)
    fun changePrivate(isPrivate: Boolean) =
        sharedPreferences.edit().putBoolean("$KEY_IS_PRIVATE", isPrivate).apply()

    fun checkPassword(password: String) = true
//    fun checkPassword(password: String) = password == getPassword()
    fun changePassword(password: String) =
        sharedPreferences.edit().putString("$KEY_PASSWORD", "").apply()

    private fun getPassword() = sharedPreferences.getString("$KEY_PASSWORD", "")

}