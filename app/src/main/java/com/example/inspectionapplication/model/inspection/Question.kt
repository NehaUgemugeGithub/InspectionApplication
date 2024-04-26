package com.example.inspectionapplication.model.inspection

data class Question(
    val answerChoices: List<AnswerChoice>,
    val id: Int,
    val name: String,
    var selectedAnswerChoiceId: Any
)

