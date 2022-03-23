package com.example.fetchcodingchallenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchcodingchallenge.data.ListItem
import com.example.fetchcodingchallenge.data.ListItemRepository
import com.example.fetchcodingchallenge.utils.Event
import kotlinx.coroutines.launch


enum class FetchApiStatus {
    LOADING, ERROR, DONE
}

class ListItemViewModel : ViewModel() {

    private val _statusMessage = MutableLiveData<Event<String>?>()
    val statusMessage : MutableLiveData<Event<String>?>
        get() = _statusMessage

    private val _status = MutableLiveData<FetchApiStatus>()
    val status: LiveData<FetchApiStatus>
        get() = _status

    private val _listItems = MutableLiveData<List<ListItem>>()
    val listItems: LiveData<List<ListItem>>
        get() = _listItems

    private val repository = ListItemRepository()

    init {
        retrieveListItems()
    }

    /**
     * Retrieves items from repository and sets as LiveData
     */
    fun retrieveListItems() {
        viewModelScope.launch {
            _status.value = FetchApiStatus.LOADING
            try {
                _listItems.value = repository.getListItems()
                _status.value = FetchApiStatus.DONE
                _statusMessage.value = null
            } catch (e: Exception) {
                e.printStackTrace()
                _status.value = FetchApiStatus.ERROR
                _statusMessage.value = Event("Error retrieving items...")

            }
        }
    }

}