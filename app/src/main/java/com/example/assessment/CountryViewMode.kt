package com.example.assessment

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {

    private val repository = CountryRepository()

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    init {
        fetchCountries()
    }

    fun fetchCountries() {
        _loading.value = true
        viewModelScope.launch {
            val result = repository.getCountries()
            if (result.isSuccess) {
                _countries.value = result.getOrNull()
                _error.value = null
            } else {
                _error.value = result.exceptionOrNull()?.message
            }
            _loading.value = false
        }
    }
}
