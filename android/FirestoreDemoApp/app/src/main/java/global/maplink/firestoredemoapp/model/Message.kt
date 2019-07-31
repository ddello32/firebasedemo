package global.maplink.firestoredemoapp.model

import com.google.firebase.firestore.Exclude
import java.util.*

data class Message(
    @get:Exclude var id : String? = null,
    var userName: String,
    var message: String,
    var timestamp: Date
) {
    constructor() : this(null, "", "", Date(0))
}