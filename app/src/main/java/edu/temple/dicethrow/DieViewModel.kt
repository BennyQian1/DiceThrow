package edu.temple.dicethrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DieViewModel: ViewModel() {
    private val currentRoll : MutableLiveData<Int> by lazy {
        MutableLiveData()
    }

    fun getCurrentRoll(): LiveData<Int> {
        return currentRoll
    }
}
fun current_Roll(roll: Int) {
    current_Roll.value = roll
}