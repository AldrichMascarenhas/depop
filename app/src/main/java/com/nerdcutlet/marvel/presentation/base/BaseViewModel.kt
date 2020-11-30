package com.nerdcutlet.marvel.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.properties.Delegates

abstract class BaseViewModel<STATE : Any, ACTIONS : Any>(initialState: STATE) : ViewModel() {

    private val mutableStateLiveData: MutableLiveData<STATE> = MutableLiveData()
    val stateLiveData: LiveData<STATE> = mutableStateLiveData

    protected var state by Delegates.observable(initialState) { _, _, new ->
        mutableStateLiveData.value = new
    }

    fun sendAction(action: ACTIONS) {
        reducer(action)
    }

    abstract fun reducer(action: ACTIONS)
}
