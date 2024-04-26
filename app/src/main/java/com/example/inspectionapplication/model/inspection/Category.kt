package com.example.inspectionapplication.model.inspection

data class Category(
    val id: Int,
    val name: String,
    val questions: List<Question>
)