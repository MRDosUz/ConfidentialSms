package uz.mrdos.confidentialsms.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.mrdos.confidentialsms.core.adapter.MessagesAdapter
import uz.mrdos.confidentialsms.databinding.ActivityMessagesBinding
import uz.mrdos.confidentialsms.core.model.SmsMessageDto

class MessagesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMessagesBinding
    private var messages = ArrayList<SmsMessageDto>()

    private val adapter = MessagesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.smsMessagesList.adapter = adapter
        loadDataToView()
    }

    private fun loadDataToView() {
        for (i in 0 until 100) {
            adapter.addSmsMessage(
                SmsMessageDto(
                    "User $i",
                    "+998 91 777 77 77",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ3OzU2X82NURyXhJKcYfVZK_yKJDA_65Qe9w&usqp=CAU",
                    "Shanba kuni darsga kirma, o'qib olim bo'larmiding !!! Mani garajimda ishlaysan o'qima",
                    false
                )
            )
        }
    }
}