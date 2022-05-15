package com.example.weatherapp.models

open class DefaultResponse {
    val executingTime: String? = null
    val isSuccess: Boolean? = null
    val responseException: ResponseException? = null
    val statusCode: Int? = null
    val version: String? = null
}

open class ResponseException(
    val exceptionMessage: String,
    val validationErrors: List<ValidationError>
)

open class ValidationError(
    val message: String
)