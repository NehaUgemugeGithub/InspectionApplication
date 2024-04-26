package com.example.inspectionapplication.model.inspection

data class InspectionX(
    val area: Area,
    val id: Int,
    val inspectionType: InspectionType,
    val survey: Survey
)