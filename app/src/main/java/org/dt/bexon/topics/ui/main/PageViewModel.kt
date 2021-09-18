package org.dt.bexon.topics.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.dt.bexon.topics.model.Data

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    val topic = MutableLiveData<Data>()
    fun setData(data: Data) {
        topic.value = data
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}