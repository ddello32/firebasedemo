package global.maplink.firestoredemoapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

const val MESSAGES_COLLECTION = "messages"

class MessageRepository {
    val db = FirebaseFirestore.getInstance()
    val messages by lazy { MessageLiveData(db.collection(MESSAGES_COLLECTION)) }

    fun saveMessage(message: Message) {
        db.collection(MESSAGES_COLLECTION).add(message).addOnSuccessListener {
            message.id = it.id
        }
    }
}

class MessageLiveData(private val messagesCollection: CollectionReference) : MutableLiveData<List<Message>>() {
    var registration: ListenerRegistration? = null

    override fun onActive() {
        super.onActive()
        registration?.remove()
        registration = messagesCollection.orderBy("timestamp").addSnapshotListener { results, error ->
            if (error != null || results == null) {
                postValue(listOf())
            } else {
                postValue(results.map {
                    it.toObject(Message::class.java).apply {
                        id = it.id
                    }
                })
            }

        }
    }

    override fun onInactive() {
        super.onInactive()
        registration?.remove()
        registration = null
    }
}