package com.example.testonlinetoursapp.constants

import kotlinx.coroutines.flow.MutableStateFlow


const val API_BASE_URL = "https://wwww.onlinetours.ru"
const val API_URL = "$API_BASE_URL/api/v2/public/"
const val AUTH_TOKEN = "7f0185a09ec234cf0c2662a14d6e6fcf"
const val BOTTOMSHEET_CONTENT_FROM = 1
const val BOTTOMSHEET_CONTENT_TO = 2




val bottomSheetContentStatus = MutableStateFlow(BOTTOMSHEET_CONTENT_FROM)