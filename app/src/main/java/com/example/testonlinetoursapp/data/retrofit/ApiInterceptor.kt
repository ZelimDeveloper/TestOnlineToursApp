package com.example.testonlinetoursapp.data.retrofit

import com.example.testonlinetoursapp.constants.AUTH_TOKEN
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class ApiInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        request = request.newBuilder()
            .addHeader("Authorization", "Token $AUTH_TOKEN")
            .build()

        return chain.proceed(request)
    }
}



