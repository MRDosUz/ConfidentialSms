package uz.mrdos.confidentialsms.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.mrdos.confidentialsms.core.cache.AppCache
import uz.mrdos.confidentialsms.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = if (AppCache.isPrivate())
            Intent(this, LoginActivity::class.java)
        else
            Intent(this, MessagesActivity::class.java)
//        val intent = Intent(this, LoginActivity::class.java)

        startActivity(intent)
        finish()
    }
}
