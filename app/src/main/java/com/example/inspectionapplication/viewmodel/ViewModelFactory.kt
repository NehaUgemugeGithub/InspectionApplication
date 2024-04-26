package com.example.inspectionapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.inspectionapplication.model.repository.InspectionRepository

class ViewModelFactory(private val repository: InspectionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InspectionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InspectionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}