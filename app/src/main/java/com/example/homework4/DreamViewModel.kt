package com.example.homework4

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.sql.Date

class DreamViewModel (private val repository: DreamRepository): ViewModel() {
    val allDreams: LiveData<List<Dream>> = repository.allDreams.asLiveData()

    // we want to make sure that the view model is running in its own scope
    // in the view model library, it has its own scope
    // viewModelScope
    // you want to launch a new coroutine to run each of the suspend functions from your repository
    // viewModelScope allows everything to run based on their lifecycles

    fun insert(dream:Dream) = viewModelScope.launch{
        repository.insert(dream)
    }

    fun delete(id:Int) = viewModelScope.launch{
        repository.delete(id)
    }

    fun select(id:Int) : LiveData<Dream> {
        return repository.select(id).asLiveData()
    }

    fun update(id:Int, title:String, date:String, content:String, reflection:String, emotion:String) = viewModelScope.launch {
        repository.update(id, title, date, content, reflection, emotion)
    }

}

class DreamViewModelFactory(private val repository: DreamRepository):ViewModelProvider.Factory{

    // override the create method to return the DreamViewModel
    override fun <T: ViewModel?> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(DreamViewModel::class.java)){
            return DreamViewModel(repository) as T
        }
        throw IllegalArgumentException("unknown view model class")
    }
}