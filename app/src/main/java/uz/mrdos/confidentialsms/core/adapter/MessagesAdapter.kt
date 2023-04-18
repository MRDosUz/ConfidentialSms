package uz.mrdos.confidentialsms.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import uz.mrdos.confidentialsms.R
import uz.mrdos.confidentialsms.core.model.SmsMessageDto
import uz.mrdos.confidentialsms.core.other.OnAdapterItemClick

class MessagesAdapter : BaseAdapter() {

    private lateinit var onAdapterItemClick: OnAdapterItemClick

    var smsMessageList = ArrayList<SmsMessageDto>()
        set(value) {
            field.addAll(value)
            notifyDataSetChanged()
        }

    fun setOnAdapterItemClick(onAdapterItemClick: OnAdapterItemClick) {
        this.onAdapterItemClick = onAdapterItemClick
    }

    fun addSmsMessage(smsMessage: SmsMessageDto) {
        smsMessageList.add(smsMessage)
        notifyDataSetChanged()
    }

    override fun getCount(): Int = smsMessageList.size

    override fun getItem(position: Int): SmsMessageDto = smsMessageList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.sms_messages_item, parent, false)


        val data: SmsMessageDto = getItem(position)

        val imageView = view.findViewById<CircleImageView>(R.id.contact_image)
        val userName = view.findViewById<TextView>(R.id.sender_name)
        val shortMessage = view.findViewById<TextView>(R.id.short_message)

        Glide.with(imageView)
            .load(data.senderPhoto)
            .placeholder(R.drawable.image)
            .into(imageView)

        userName.text = if (data.senderName == null) data.phoneNumber else data.senderName
        shortMessage.text = data.messageContent

        val layout = view.findViewById<ConstraintLayout>(R.id.item_group)

        layout.setOnClickListener {
            onAdapterItemClick.onItemClick(data)
        }
        return view
    }
}