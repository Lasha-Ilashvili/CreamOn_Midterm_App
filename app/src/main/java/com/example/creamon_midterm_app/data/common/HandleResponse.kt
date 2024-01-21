package com.example.creamon_midterm_app.data.common

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response


class HandleResponse() {

    fun <T : Any> safeApiCall(call: suspend () -> Response<T>) = flow {
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

    fun safeAuthCall(call: suspend () -> Task<AuthResult>) = flow {
        emit(Resource.Loading(loading = true))

        try {
            val task = call()
            val user = task.result.user

            if (task.isSuccessful && user != null) {
                emit(Resource.Success(data = user))
            } else {
                emit(Resource.Error(errorMessage = task.exception?.localizedMessage ?: ""))
            }
        } catch (e: FirebaseAuthException) {
            emit(Resource.Error(errorMessage = e.localizedMessage ?: ""))
        } catch (e: Throwable) {
            emit(Resource.Error("An unexpected error occurred: ${e.localizedMessage}"))
        } finally {
            emit(Resource.Loading(loading = false))
        }


//        emit(Resource.Loading(loading = false))
    }
}