package com.example.testonlinetoursapp

import android.app.Application
import com.example.testonlinetoursapp.data.retrofit.RetrofitHelperClass
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApp @Inject constructor(): Application()



