package global.maplink.firestoredemoapp

import android.os.Build
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import global.maplink.firestoredemoapp.model.Message
import global.maplink.firestoredemoapp.model.MessageRepository
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MessagesListActivity : AppCompatActivity() {
    private lateinit var viewModel : MessageViewModel
    private val adapter = MessageListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        messages_list.adapter = adapter
        viewModel = ViewModelProviders.of(this).get(MessageViewModel::class.java)
        viewModel.messages.observe(this, Observer{ adapter.submitList(it)})
        messages_send.setOnClickListener {
            messages_input.onEditorAction(EditorInfo.IME_ACTION_SEND)
        }
        messages_input.setOnEditorActionListener { view, actionId, event ->
            when(actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    viewModel.onMessageTyped(messages_input.text.toString())
                    messages_input.setText("")
                    true
                }
                else -> false
            }
        }
    }
}

class MessageViewModel : ViewModel() {
    private val repository = MessageRepository()
    val messages: LiveData<List<Message>> = repository.messages

    fun onMessageTyped(text: String) {
        repository.saveMessage(Message(userName = Build.DEVICE, message = text, timestamp = Date()))
    }
}