package com.example.inspectionapplication.model.inspection

import com.example.inspectionapplication.model.inspection.Category

data class Survey(
    val categories: List<Category>,
    val id: Int
)