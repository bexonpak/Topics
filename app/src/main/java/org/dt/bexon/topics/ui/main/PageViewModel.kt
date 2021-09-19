package org.dt.bexon.topics.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.dt.bexon.topics.model.Data

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()

    val topic = MutableLiveData<Data>()
    fun setData(data: Data) {
        topic.value = data
    }

}