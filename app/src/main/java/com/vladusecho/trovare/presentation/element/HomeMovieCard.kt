package com.vladusecho.trovare.presentation.element

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.vladusecho.trovare.domain.model.Movie
import com.vladusecho.trovare.presentation.ui.theme.TrovareTheme

@Composable
fun HomeMovieCard(
    modifier: Modifier = Modifier,
    movie: Movie,
    onCardClick: () -> Unit
) {
    Box(
        modifier = modifier
            .width(206.dp)
            .height(260.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = movie.poster,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
fun HomeMovieCardPreview() {
    TrovareTheme(

    ) {
        HomeMovieCard(
            movie = Movie(
                id = 0,
                name = "Звёздные войны: Эпизод 4 – Новая надежда",
                year = 1977,
                description = "Татуин. Планета-пустыня. Уже постаревший рыцарь Джедай Оби Ван Кеноби спасает молодого Люка Скайуокера, когда тот пытается отыскать пропавшего дроида. С этого момента Люк осознает свое истинное назначение: он один из рыцарей Джедай. В то время как гражданская война охватила галактику, а войска повстанцев ведут бои против сил злого Императора, к Люку и Оби Вану присоединяется отчаянный пилот-наемник Хан Соло, и в сопровождении двух дроидов, R2D2 и C-3PO, этот необычный отряд отправляется на поиски предводителя повстанцев – принцессы Леи. Героям предстоит отчаянная схватка с устрашающим Дартом Вейдером – правой рукой Императора и его секретным оружием – «Звездой Смерти».",
                movieLength = "121",
                ageRating = 6,
                poster = null,
                genres = listOf("Боевик", "Фэнтези", "Приключения", "Драма"),
                rating = com.vladusecho.trovare.domain.model.MovieRating(
                    kp = "8.2",
                    imdb = "8.2"
                )
            )
        ) { }
    }
}