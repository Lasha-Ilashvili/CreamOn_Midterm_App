package com.example.creamon_midterm_app.data.common

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response


class HandleResponse {

    fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Flow<Resource<T>> = flow {
        emit(Resource.Loading(loading = true))

        try {
            val response = call()
            val body = response.body()

            if (response.isSuccessful && body != null) {
                emit(Resource.Success(data = body))
            } else {
                emit(
                    Resource.Error(
                        "Server responded with an error: ${
                            response.errorBody()?.string()
                        }."
                    )
                )
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    "There was a problem connecting to the server. " +
                            "Please try again later."
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    "There was a problem with your internet connection. " +
                            "Please check your connection and try again."
                )
            )
        } catch (e: Throwable) {
            emit(Resource.Error("An unexpected error occurred: ${e.localizedMessage}"))
        } finally {
            emit(Resource.Loading(loading = false))
        }
    }

    fun <T : Any> safeAuthCall(call: suspend () -> Task<T>): Flow<Resource<T>> = flow {
        emit(Resource.Loading(true))

        try {
            val task = call()
            val result = task.await()

            if (task.isSuccessful) {
                emit(Resource.Success(data = result))
            } else {
                emit(Resource.Error(errorMessage = task.exception?.localizedMessage ?: ""))
            }
        } catch (e: FirebaseAuthException) {
            emit(Resource.Error(errorMessage = e.localizedMessage ?: ""))
        } catch (e: Throwable) {
            emit(Resource.Error(errorMessage = "An unexpected error occurred: ${e.localizedMessage}"))
        } finally {
            emit(Resource.Loading(false))
        }
    }
}