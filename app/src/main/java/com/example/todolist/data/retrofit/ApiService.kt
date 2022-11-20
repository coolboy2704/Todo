package com.example.todolist.data.retrofit

import com.example.todolist.core.loginRequest
import com.example.todolist.core.loginResponse
import com.example.todolist.core.updateRequest
import com.example.todolist.core.updateResponse
import com.example.todolist.data.request.Register
import com.example.todolist.data.response.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {
    @POST("/api/register")
    suspend fun registerUser(
        @Body user: Register
    ): Response<loginResponse>

    @POST("/api/login")
    suspend fun login(
        @Body user: loginRequest
    ): Response<loginResponse>

//    @GET("/api/tasks")
//    suspend fun me(
//        @Header("Authorization") token: String
//    ): Response<User>
//
//    @PUT("/api/tasks/2")
//    suspend fun updateMe(
//        @Header("Authorization") token: String,
//        @Body user: updateRequest
//    ): Response<updateResponse>
//
//    @DELETE("/user/me")
//    suspend fun deleteMe(
//        @Header("Authorization") token: String
//    ): Response<User>
}
