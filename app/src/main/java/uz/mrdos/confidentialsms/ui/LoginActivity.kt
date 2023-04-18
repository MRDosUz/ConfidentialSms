package uz.mrdos.confidentialsms.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.forEach
import androidx.core.view.get
import androidx.core.view.isGone
import androidx.core.view.isVisible
import uz.mrdos.confidentialsms.R
import uz.mrdos.confidentialsms.core.cache.AppCache
import uz.mrdos.confidentialsms.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var password = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadActions()
    }

    private fun loadActions() {
        clearPassword()
        binding.keyboardGroup.forEach { view ->
            val currentLinearLayout = view as LinearLayout
            currentLinearLayout.forEach { textView ->
                val currentTextView = textView as TextView
                currentTextView.setOnClickListener {
                    if (currentTextView.tag == "clear" || currentTextView.tag == "delete") {
                        if (currentTextView.tag == "clear")
                            clearPassword()
                        else deletePassword()
                    } else {
                        if (password.length < 4) {
                            password += currentTextView.tag
                            openNextPasswordLetter()
                            checkIsTruePassword()
                        }
                    }
                }
            }
        }
    }

    private fun checkIsTruePassword() {
        if (password.length == 4) {
            if (AppCache.checkPassword(password)) {
                val intent = Intent(this, MessagesActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                passwordWrong(true)
            }
        }
    }

    private fun passwordWrong(isWrong: Boolean) {
        if (isWrong) {
            binding.loginTitle1.text = "Noto'g'ri parol kiritildi!"
            binding.loginTitle1.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.login_title_text_color_wrong
                )
            )
            binding.loginTitle2.visibility = View.INVISIBLE
        } else if (password.length == 4) {
            binding.loginTitle1.text = "Itlimos parolni kiriting"
            binding.loginTitle1.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.login_title_text_color
                )
            )
            binding.loginTitle2.visibility = View.VISIBLE
        }
    }

    private fun openNextPasswordLetter() {
        val linerLayout = binding.passwordGroup
        for (i in 0..3) {
            val currentLinearLayout = linerLayout[i] as LinearLayout
            if (currentLinearLayout.isGone) {
                currentLinearLayout.visibility = View.VISIBLE
                break
            }
        }
    }

    private fun deletePassword() {
        passwordWrong(false)
        if (password.isNotEmpty())
            password = password.substring(0, password.length - 1)
        val linerLayout = binding.passwordGroup
        for (i in 3 downTo 0) {
            val currentLinearLayout = linerLayout[i] as LinearLayout
            if (currentLinearLayout.isVisible) {
                currentLinearLayout.visibility = View.GONE
                break
            }
        }
    }

    private fun clearPassword() {
        passwordWrong(false)
        password = ""
        binding.passwordGroup.forEach { view ->
            val currentLinearLayout = view as LinearLayout
            currentLinearLayout.visibility = View.GONE
        }
    }
}