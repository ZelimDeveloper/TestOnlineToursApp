package com.example.testonlinetoursapp.components.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.testonlinetoursapp.R
import com.example.testonlinetoursapp.constants.BOTTOMSHEET_CONTENT_FROM
import com.example.testonlinetoursapp.constants.BOTTOMSHEET_CONTENT_TO
import com.example.testonlinetoursapp.constants.bottomSheetContentStatus
import com.example.testonlinetoursapp.data.models.CityModel
import com.example.testonlinetoursapp.data.models.CountryModel
import com.example.testonlinetoursapp.ui.theme.BlueColor
import com.example.testonlinetoursapp.ui.theme.BottomSelectedColor
import com.example.testonlinetoursapp.ui.theme.CardBackground
import com.example.testonlinetoursapp.ui.theme.CardTextColor


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FirstCard(titleFrom:String, titleTo:String, onClickFrom: () -> Unit, onClickTo: () -> Unit) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClickFrom,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        backgroundColor = CardBackground
    )
    {
        Row {
            Text("Откуда", modifier = Modifier
                .padding(16.dp)
                .weight(1f), color = CardTextColor)
            Text(titleFrom, modifier = Modifier
                .padding(vertical = 16.dp)
                .weight(1f), color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.W600)
        }

    }
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp))

    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClickTo,
        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
        backgroundColor = CardBackground
    )
    {
        Row {
            Text("Куда", modifier = Modifier
                .padding(16.dp)
                .weight(1f), color = CardTextColor)
            Text(titleTo, modifier = Modifier
                .padding(vertical = 16.dp)
                .weight(1f), color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.W600)
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SecondCard() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(1.dp)) {
        Card(
            modifier = Modifier.weight(1f),
            onClick = {  },
            shape = RoundedCornerShape(topStart = 16.dp),
            backgroundColor = CardBackground
        )
        {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("ДАТЫ ВЫЛЕТА", modifier = Modifier, color = CardTextColor)
                Text("17 сентября", modifier = Modifier)
            }

        }

        Card(
            modifier = Modifier.weight(1f),
            onClick = {  },
            shape = RoundedCornerShape(topEnd = 16.dp),
            backgroundColor = CardBackground
        )
        {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("КОЛИЧЕСТВО", modifier = Modifier, color = CardTextColor)
                Text("10 ночей", modifier = Modifier)
            }
        }
    }


    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp))

    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = {  },
        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
        backgroundColor = CardBackground
    )
    {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("КТО ЕДЕТ", modifier = Modifier, color = CardTextColor)
            Text("2 взрослых", modifier = Modifier)
        }

    }
}

@Composable
fun Title() {
    Text(
        text = "Поиск туров",
        modifier = Modifier,
        color = Color.Black,
        fontWeight = FontWeight.ExtraBold,
        lineHeight = 24.sp,
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        letterSpacing = (-0.1).sp
    )
}


@Composable
fun ConfirmButton(onClickConfirm:() -> Unit) {
    Button(
        onClick = onClickConfirm,
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(containerColor = BottomSelectedColor),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 10.dp)

    ) {
        Text(
            text = "Найти тур",
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            lineHeight = 24.sp,
            textAlign = TextAlign.Center)
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BottomSheetContentFrom(onCloseClick: () -> Unit, cities: List<CityModel>, onSelectCity: (CityModel) -> Unit) {

    val textState = remember { mutableStateOf(TextFieldValue("")) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp))
    {

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween)
        {
            SearchCard(state = textState, modifier = Modifier.weight(3f), placeHolder = R.string.search_card_placeholder)
            Spacer(modifier = Modifier.width(12.dp))
            TextButton(onClick = onCloseClick, modifier = Modifier.weight(1f)) {
                Text(
                    text = "Закрыть",
                    color = BlueColor,
                    fontWeight = FontWeight.W600,
                    lineHeight = 18.sp,
                    fontSize = 14.sp)
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp))
        {
            val searchedText = textState.value.text
            val filteredCities = if (searchedText.isEmpty()) {
                cities
            } else {
                val resultList = mutableListOf<CityModel>()
                for (city in cities) {
                    if (city.name.lowercase().contains(searchedText.lowercase())) { resultList.add(city) }
                }

                resultList
            }

            items(filteredCities) { city ->
                TextButton(onClick = { onSelectCity(city) }) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = city.name,
                        color = Color.Black,
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun BottomSheetContentTo(onCloseClick: () -> Unit, countries: List<CountryModel>, onSelectCountry: (CountryModel) -> Unit) {

    val textState = remember { mutableStateOf(TextFieldValue("")) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp))
    {

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween)
        {
            SearchCard(state = textState, modifier = Modifier.weight(3f), placeHolder = R.string.search_card_placeholder2)
            Spacer(modifier = Modifier.width(12.dp))
            TextButton(onClick = onCloseClick, modifier = Modifier.weight(1f)) {
                Text(
                    text = "Закрыть",
                    color = BlueColor,
                    fontWeight = FontWeight.W600,
                    lineHeight = 18.sp,
                    fontSize = 14.sp)
            }
        }

        Text(
            text = "Популярные направления",
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = CardTextColor,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp))
        {
            val searchedText = textState.value.text
            val filteredCountries = if (searchedText.isEmpty()) {
                countries
            } else {
                val resultList = mutableListOf<CountryModel>()
                for (city in countries) {
                    if (city.name.lowercase().contains(searchedText.lowercase())) { resultList.add(city) }
                }

                resultList
            }

            items(filteredCountries) { country ->
                TextButton(onClick = { onSelectCountry(country) }) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = country.name,
                        color = Color.Black,
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun SearchCard(state: MutableState<TextFieldValue>, modifier: Modifier = Modifier, placeHolder:Int) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = CardBackground
    )
    {
        TextField(
            value = state.value,
            onValueChange = { value -> state.value = value },
            modifier = Modifier
                .focusRequester(focusRequester),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.W500
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                textColor = MaterialTheme.colorScheme.primary,
                cursorColor = BlueColor
            ),
            placeholder = {
                Text(
                    text = stringResource(placeHolder),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Start,
                    color = CardTextColor
                )
            },
            singleLine = true)
    }
    LaunchedEffect(Unit)
    {
        focusRequester.requestFocus()
    }
}

@Composable
fun BottomSheetContent(
    onCloseClick: () -> Unit,
    onSelectCity: (CityModel) -> Unit,
    cities: List<CityModel>,
    onSelectCountry: (CountryModel) -> Unit,
    countries: List<CountryModel>)
{
    val contentType by bottomSheetContentStatus.collectAsState()

    when(contentType) {
        BOTTOMSHEET_CONTENT_FROM -> BottomSheetContentFrom(
            onCloseClick = onCloseClick,
            onSelectCity = onSelectCity,
            cities = cities
        )
        BOTTOMSHEET_CONTENT_TO -> BottomSheetContentTo(
            onCloseClick = onCloseClick,
            onSelectCountry = onSelectCountry,
            countries = countries
        )
    }
}