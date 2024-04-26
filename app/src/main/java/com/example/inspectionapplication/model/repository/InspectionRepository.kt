package com.example.inspectionapplication.model.repository

import com.example.inspectionapplication.api.ApiInterface
import com.example.inspectionapplication.model.inspection.Inspection
import com.example.inspectionapplication.model.inspection.Question
import com.example.inspectionapplication.model.login.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Response

class InspectionRepository (private val apiService: ApiInterface) {

    suspend fun login(username: String, password: String): Response<Unit> {
        return apiService.login(LoginRequest(username, password))
    }

    suspend fun submitInspection(inspection: Inspection): Response<Unit> {
        return apiService.submitInspection(inspection)
    }


    suspend fun fetchQuestions(): List<Question> {
        return withContext(Dispatchers.IO) {
            try {
                val deferred = async { apiService.fetchQuestions().await() }
                deferred.await()
            } catch (e: Exception) {
                // Handle error
                emptyList()
            }
        }
    }
}