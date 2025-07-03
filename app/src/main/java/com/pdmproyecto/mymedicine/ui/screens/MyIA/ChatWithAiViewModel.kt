package com.pdmproyecto.mymedicine.ui.screens.MyIA

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdmproyecto.mymedicine.BuildConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class ChatWithAiViewModel : ViewModel() {

    private val _response = MutableStateFlow("")
    val response: StateFlow<String> = _response

    private val client = OkHttpClient()

    fun sendMessage(message: String) {
        viewModelScope.launch {
            val partsArray = JSONArray().apply {
                put(JSONObject().apply {
                    put("text", message)
                })
            }

            val userMessage = JSONObject().apply {
                put("role", "user")
                put("parts", partsArray)
            }

            val contentsArray = JSONArray().apply {
                put(userMessage)
            }

            val requestBodyJson = JSONObject().apply {
                put("contents", contentsArray)
            }

            val request = Request.Builder()
                .url("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash-lite:generateContent?key=${BuildConfig.GEMINI_API_KEY}")
                .post(requestBodyJson.toString().toRequestBody("application/json".toMediaType()))
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    viewModelScope.launch {
                        _response.value = "Error de red: ${e.message}"
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    response.use {
                        val body = it.body?.string()
                        println("🔍 Respuesta cruda de Gemini:\n$body")
                        try {
                            val json = JSONObject(body)
                            val content = json.getJSONArray("candidates")
                                .getJSONObject(0)
                                .getJSONObject("content")
                                .getJSONArray("parts")
                                .getJSONObject(0)
                                .getString("text")

                            viewModelScope.launch {
                                _response.value = content
                            }
                        } catch (e: Exception) {
                            println("⚠️ Error al procesar la respuesta: ${e.message}")
                            viewModelScope.launch {
                                _response.value = "No se pudo procesar la respuesta. Intenta otra pregunta."
                            }
                        }
                    }
                }
            })
        }
    }
}
