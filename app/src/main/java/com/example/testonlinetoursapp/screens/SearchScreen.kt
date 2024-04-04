package com.example.testonlinetoursapp.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testonlinetoursapp.ui.theme.BottomSelectedColor
import com.example.testonlinetoursapp.ui.theme.CardBackground
import com.example.testonlinetoursapp.ui.theme.CardTextColor

@Composable
fun SearchScreen(onClickFrom: () -> Unit, onClickTo: () -> Unit, onClickConfirm: () -> Unit) {

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
                    FirstCard(onClickFrom, onClickTo)
                    Spacer(Modifier.height(8.dp))
                    SecondCard()
                }

                ConfirmButton(onClickConfirm)

            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FirstCard(onClickFrom: () -> Unit, onClickTo: () -> Unit) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClickFrom,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        backgroundColor = CardBackground
    )
    {
        Text("Откуда", modifier = Modifier.padding(16.dp), color = CardTextColor)
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
        Text("Куда", modifier = Modifier.padding(16.dp), color = CardTextColor)
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


@Composable
fun BottomSheetContent() {


}