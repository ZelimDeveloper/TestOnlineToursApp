package com.example.testonlinetoursapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.testonlinetoursapp.R
import com.example.testonlinetoursapp.constants.globalToursList
import com.example.testonlinetoursapp.data.models.ToursResultModel
import com.example.testonlinetoursapp.ui.theme.CardBackground
import com.example.testonlinetoursapp.ui.theme.CardTextColor
import com.example.testonlinetoursapp.ui.theme.greyColor
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SearchResultScreen(onBackClick: () -> Unit) {

   val tours by globalToursList.collectAsState()

    Scaffold(
        topBar = { ResultTopBar(onBackClick = onBackClick) }
    )
    { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)) {

            items(tours) {model ->
                TourCard(model = model)
            }
        }
    }
}


@Composable
fun TourCard(model:ToursResultModel) {

    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = 8.dp
    ) {

        Column(Modifier.padding(16.dp)) {
            GlideImage(
                imageModel = "https://www.atorus.ru/sites/default/files/upload/image/News/56959/s1.jpg",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp)),
                requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL) ,
                contentScale = ContentScale.FillWidth)

            Image(painter = painterResource(R.drawable.stars), contentDescription = "", modifier = Modifier.padding(top = 8.dp))

            Text(
                text = model.offer.original_name,
                fontWeight = FontWeight.W800,
                fontSize = 18.sp,
                lineHeight = 24.sp)
            
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier.clip(shape = RoundedCornerShape(40.dp)).background(greyColor)
                ) {
                    Text(text = "1 линия", modifier = Modifier.padding(5.dp), color = CardTextColor)
                }
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier.clip(shape = RoundedCornerShape(40.dp)).background(greyColor)
                ) {
                    Text(text = "200м до пляжа", modifier = Modifier.padding(5.dp),  color = CardTextColor)
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.clip(shape = RoundedCornerShape(40.dp)).background(greyColor)
                ) {
                    Text(text = "Бесплатный Wi-Fi ", modifier = Modifier.padding(5.dp), color = CardTextColor)
                }
            }

            Text(
                text = "от ${model.offer.original_price.price} ${model.offer.original_price.currency} за 2-х",
                fontWeight = FontWeight.W800,
                fontSize = 20.sp,
                lineHeight = 24.sp)




        }
    }
}


@Composable
fun ResultTopBar(onBackClick:()-> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.Default.ArrowCircleLeft, contentDescription = "",
                tint = CardTextColor,
                modifier = Modifier
                    .size(width = 32.dp, height = 32.dp)
                    .align(Alignment.CenterStart))
        }

        Column(modifier = Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(text = "Турция", fontWeight = FontWeight.W600, fontSize = 16.sp, lineHeight = 24.sp)
            Text(text = "17 сент - 10 ночей - 2 чел", fontWeight = FontWeight.W500, fontSize = 12.sp, lineHeight = 16.sp)
        }
    }
}