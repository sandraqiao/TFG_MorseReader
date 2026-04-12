import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.morsereader.database.AppDatabase
import com.example.morsereader.morse.MorseMessage
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.collections.emptyList

class MorseViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).morseDao()

    val messages: StateFlow<List<MorseMessage>> = dao.getAll()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun add(message: MorseMessage) {
        viewModelScope.launch {
            dao.insert(message)
        }
    }

    fun delete(message: MorseMessage) {
        viewModelScope.launch {
            dao.delete(message)
        }
    }

    fun update(message: MorseMessage) {
        viewModelScope.launch {
            dao.update(message)
        }
    }
}