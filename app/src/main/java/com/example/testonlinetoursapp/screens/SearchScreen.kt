package com.example.testonlinetoursapp.screens


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.testonlinetoursapp.R
import com.example.testonlinetoursapp.components.ui.BottomSheetContent
import com.example.testonlinetoursapp.components.ui.ConfirmButton
import com.example.testonlinetoursapp.components.ui.FirstCard
import com.example.testonlinetoursapp.components.ui.SecondCard
import com.example.testonlinetoursapp.components.ui.Title
import com.example.testonlinetoursapp.constants.BOTTOMSHEET_CONTENT_FROM
import com.example.testonlinetoursapp.constants.BOTTOMSHEET_CONTENT_TO
import com.example.testonlinetoursapp.constants.bottomSheetContentStatus
import com.example.testonlinetoursapp.constants.globalToursList
import com.example.testonlinetoursapp.data.models.CityModel
import com.example.testonlinetoursapp.data.models.CountryModel
import com.example.testonlinetoursapp.data.models.ToursResultModel
import com.example.testonlinetoursapp.data.retrofit.DataProvider
import com.example.testonlinetoursapp.ui.theme.BlueColor
import com.example.testonlinetoursapp.ui.theme.BottomSelectedColor
import com.example.testonlinetoursapp.ui.theme.CardBackground
import com.example.testonlinetoursapp.ui.theme.CardTextColor
import com.example.testonlinetoursapp.ui.theme.graybsIconColor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(onClickConfirm: () -> Unit, vm: SearchScreenVM = hiltViewModel()) {

    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var selectedCity by remember { mutableStateOf(CityModel()) }
    var selectedCountry by remember { mutableStateOf(CountryModel()) }
    var buttonLoading by remember { mutableStateOf(false) }

    if (openBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { openBottomSheet = false },
            sheetState = bottomSheetState)
        {
            BottomSheetContent(
                onCloseClick = {
                    scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                        if (!bottomSheetState.isVisible) { openBottomSheet = false }
                    }
                },
                onSelectCity = { city ->
                    selectedCity = city
                    openBottomSheet = false
                },
                cities = vm.citiesList,
                onSelectCountry = { country ->
                    selectedCountry = country
                    openBottomSheet = false
                },
                countries = vm.countryList
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize())
    {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp))
        {
            Title()

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(top = 36.dp), verticalArrangement = Arrangement.SpaceBetween) {
                Column {
                    FirstCard(
                        titleFrom = selectedCity.name,
                        titleTo = selectedCountry.name,
                        onClickFrom = {
                            bottomSheetContentStatus.value = BOTTOMSHEET_CONTENT_FROM
                            openBottomSheet = true
                            },
                        onClickTo = {
                            bottomSheetContentStatus.value = BOTTOMSHEET_CONTENT_TO
                            openBottomSheet = true
                        })
                    Spacer(Modifier.height(8.dp))
                    SecondCard()
                }

                ConfirmButton(
                    buttonLoading = buttonLoading,
                    onClickConfirm = {
                        buttonLoading = true
                        vm.createSearch(
                            selectedCityId = selectedCity.id,
                            success = {
                                onClickConfirm()
                            },
                            error = {
                                buttonLoading = false
                            }
                        )
                    }
                )
            }
        }
    }
}



@HiltViewModel
class SearchScreenVM @Inject constructor (private val dataProvider:DataProvider):ViewModel() {

    val citiesList = mutableStateListOf<CityModel>()
    val countryList = mutableStateListOf<CountryModel>()

    init {
        getCities()
        getCountry()
    }


    fun getTour(searchKey:String, success: (List<ToursResultModel>) -> Unit) {
        dataProvider.getTours(
            searchKey = searchKey,
            success = { success(it) })
    }
    fun createSearch(selectedCityId:Int, success: () -> Unit, error: () -> Unit) {
        dataProvider.createSearch(
            selectedCityId = selectedCityId,
            success = { key ->
                getTour(key) { list ->
                    globalToursList.value = list
                    success()
                }
            },
            error = {
                error()
            }
        )
    }
     fun getCities() {
        dataProvider.getCities(
            success = {
                citiesList.clear()
                citiesList.addAll(it) },
            error = {},
            exception = {}
        )
    }
    fun getCountry() {
        dataProvider.getCountries(
            success = {
                countryList.clear()
                countryList.addAll(it)
            },
            error = {}
        )
    }
}

