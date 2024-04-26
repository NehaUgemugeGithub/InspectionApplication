package com.example.inspectionapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inspectionapplication.model.inspection.Inspection
import com.example.inspectionapplication.model.inspection.Question
import com.example.inspectionapplication.model.repository.InspectionRepository
import kotlinx.coroutines.launch

class InspectionViewModel(private val repository: InspectionRepository) : ViewModel() {

    fun login(username: String, password: String, onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.login(username, password)
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onError()
                }
            } catch (e: Exception) {
                onError()
            }
        }
    }

    fun submitInspection(inspection: Inspection,onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.submitInspection(inspection)
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onError()
                }
            } catch (e: Exception) {
                onError()
            }
        }
    }

    fun fetchQuestions(callback: (List<Question>) -> Unit) {
        viewModelScope.launch {
            val posts = repository.fetchQuestions()
            callback(posts)
        }
    }
}