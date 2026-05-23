package com.vladusecho.trovare.presentation.element

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.vladusecho.trovare.R
import com.vladusecho.trovare.domain.model.Movie
import com.vladusecho.trovare.presentation.ui.theme.TrovareTheme
import com.vladusecho.trovare.presentation.ui.theme.TrovareTypography

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movie: Movie,
    onCardClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(144.dp)
            .background(Color.White)
    ) {
        Row(

        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(118.dp),
                contentAlignment = Alignment.Center
            ) {
                if (movie.poster != null) {
                    AsyncImage(
                        model = movie.poster,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.img_broken_poster),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .size(96.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = movie.name,
                    fontFamily = TrovareTypography,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(end = 8.dp)
                ) {
                    items(
                        items = movie.genres
                    ) {
                        GenreCard(genre = it)
                    }
                }
            }
        }
    }
}

@Composable
fun GenreCard(
    genre: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(5.dp))
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = genre,
            fontFamily = TrovareTypography,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MovieCardPreview() {
    TrovareTheme() {
        MovieCard(
            movie = Movie(
                id = 0,
                name = "Звёздные войны: Эпизод 4 – Новая надежда",
                year = 1977,
                description = "Татуин. Планета-пустыня. Уже постаревший рыцарь Джедай Оби Ван Кеноби спасает молодого Люка Скайуокера, когда тот пытается отыскать пропавшего дроида. С этого момента Люк осознает свое истинное назначение: он один из рыцарей Джедай. В то время как гражданская война охватила галактику, а войска повстанцев ведут бои против сил злого Императора, к Люку и Оби Вану присоединяется отчаянный пилот-наемник Хан Соло, и в сопровождении двух дроидов, R2D2 и C-3PO, этот необычный отряд отправляется на поиски предводителя повстанцев – принцессы Леи. Героям предстоит отчаянная схватка с устрашающим Дартом Вейдером – правой рукой Императора и его секретным оружием – «Звездой Смерти».",
                movieLength = 121,
                ageRating = 6,
                poster = null,
                genres = listOf("Боевик", "Фэнтези", "Приключения", "Драма")
            ),
            onCardClick = {}
        )
    }
}