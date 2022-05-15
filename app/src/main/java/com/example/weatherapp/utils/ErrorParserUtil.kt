package com.example.weatherapp.utils

import com.example.weatherapp.models.DefaultResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody

object ErrorParserUtil {

    fun parseError(error: ResponseBody): String {
        val type = object : TypeToken<DefaultResponse>() {}.type
        val errorResponse: DefaultResponse = Gson().fromJson(error.charStream(), type)

        return if (errorResponse.responseException?.validationErrors?.get(
                0
            ) == null
        ) {
            errorResponse.responseException!!.exceptionMessage
        } else {
            errorResponse.responseException.validationErrors[0].message
        }
    }
}