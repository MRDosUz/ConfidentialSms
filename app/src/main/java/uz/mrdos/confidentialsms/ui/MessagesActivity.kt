package uz.mrdos.confidentialsms.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.PopupMenu
import android.widget.Toast
import uz.mrdos.confidentialsms.R
import uz.mrdos.confidentialsms.core.adapter.MessagesAdapter
import uz.mrdos.confidentialsms.core.model.SmsMessageDto
import uz.mrdos.confidentialsms.databinding.ActivityMessagesBinding

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
        loadActions()
        addToAdapter()
    }

    private fun addToAdapter() {
        adapter.setData(messages)
    }

    private fun loadActions() {
        binding.itemMore.setOnClickListener{
            val popupMenu = PopupMenu(this, binding.itemMore)
            popupMenu.menuInflater.inflate(R.menu.activity_messages_menu, popupMenu.menu)
            popupMenu.show()

            popupMenu.setOnMenuItemClickListener {item ->
                when(item.itemId){
                    R.id.sort_by_name -> {
                        messages.sortBy {
                            it.senderName
                        }
                        addToAdapter()
                    }

                    R.id.sort_by_time -> {
                        messages.shuffle()
                        addToAdapter()
                    }
                }
                return@setOnMenuItemClickListener true
            }
        }
    }






    private fun loadDataToView() {
        for (i in 0 until 100) {
            messages.add(
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