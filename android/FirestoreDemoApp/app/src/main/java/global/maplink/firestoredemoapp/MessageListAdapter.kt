package global.maplink.firestoredemoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import global.maplink.firestoredemoapp.model.Message
import java.text.SimpleDateFormat
import java.util.*

class MessageListAdapter: ListAdapter<Message, MessageViewHolder>(object : DiffUtil.ItemCallback<Message>(){
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.message_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val messageDateFormat = SimpleDateFormat("hh:mm:ss", Locale.getDefault())

    private val sender = itemView.findViewById<TextView>(R.id.message_sender)
    private val text = itemView.findViewById<TextView>(R.id.message_text)
    private val date = itemView.findViewById<TextView>(R.id.message_date)

    fun bind(message:Message) {
        sender.text = message.userName
        text.text = message.message
        date.text = messageDateFormat.format(message.timestamp)
    }

}