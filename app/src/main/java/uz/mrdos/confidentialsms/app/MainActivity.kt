package uz.mrdos.confidentialsms.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.mrdos.confidentialsms.cache.AppCache
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
            Intent(this, BasisActivity::class.java)
//        val intent = Intent(this, LoginActivity::class.java)

        startActivity(intent)
    }
}
