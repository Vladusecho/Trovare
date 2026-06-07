package com.vladusecho.trovare.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.vladusecho.trovare.R
import com.vladusecho.trovare.domain.model.MovieDetails
import com.vladusecho.trovare.presentation.ui.theme.TrovareTheme
import com.vladusecho.trovare.presentation.ui.theme.TrovareTypography
import com.vladusecho.trovare.presentation.viewModel.MovieDetailsScreenViewModel

@Composable
fun MovieDetailsScreen(
    movieId: Int,
    viewModel: MovieDetailsScreenViewModel = hiltViewModel(
        creationCallback = { factory: MovieDetailsScreenViewModel.Factory ->
            factory.create(movieId)
        }
    ),
    onBackClick: () -> Unit,
) {

    val state = viewModel.state.collectAsState()
    val currentState = state.value

    MovieDetailsScreenContent(
        currentState = currentState,
        onBackClick = onBackClick
    )
}

@Composable
fun MovieDetailsScreenContent(
    currentState: MovieDetailsScreenViewModel.MovieDetailsScreenState,
    onBackClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (currentState) {
            is MovieDetailsScreenViewModel.MovieDetailsScreenState.Content -> {
                AsyncImage(
                    model = currentState.movieDetails.poster,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colorStops =
                                    arrayOf(
                                        0.45f to Color.Black,
                                        0.9f to Color.Transparent,
                                    )
                            )
                        )
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 64.dp, start = 16.dp, end = 16.dp)
                ) {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back_arrow),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        AsyncImage(
                            model = currentState.movieDetails.poster,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(width = 206.dp, height = 260.dp)
                                .border(
                                    width = 10.dp,
                                    color = Color(0xff402537),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .clip(shape = RoundedCornerShape(16.dp))
                        )
                    }
                    Text(
                        text = currentState.movieDetails.name,
                        color = Color.White,
                        fontFamily = TrovareTypography,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        textAlign = TextAlign.Center

                    )
                }

            }

            MovieDetailsScreenViewModel.MovieDetailsScreenState.Error -> {
            }

            MovieDetailsScreenViewModel.MovieDetailsScreenState.Initial -> {

            }

            MovieDetailsScreenViewModel.MovieDetailsScreenState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colorStops =
                                    arrayOf(
                                        0.45f to Color.Black,
                                        0.9f to Color.White,
                                    )
                            )
                        )
                )
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun MovieScreenContentPreview(

) {
    TrovareTheme {
        MovieDetailsScreenContent(
            onBackClick = {},
            currentState = MovieDetailsScreenViewModel.MovieDetailsScreenState.Content(
                movieDetails = MovieDetails(
                    id = 0,
                    name = "Звёздные войны: Эпизод 4 – Новая надежда",
                    year = 1977,
                    description = "Татуин. Планета-пустыня. Уже постаревший рыцарь Джедай Оби Ван Кеноби спасает молодого Люка Скайуокера, когда тот пытается отыскать пропавшего дроида. С этого момента Люк осознает свое истинное назначение: он один из рыцарей Джедай. В то время как гражданская война охватила галактику, а войска повстанцев ведут бои против сил злого Императора, к Люку и Оби Вану присоединяется отчаянный пилот-наемник Хан Соло, и в сопровождении двух дроидов, R2D2 и C-3PO, этот необычный отряд отправляется на поиски предводителя повстанцев – принцессы Леи. Героям предстоит отчаянная схватка с устрашающим Дартом Вейдером – правой рукой Императора и его секретным оружием – «Звездой Смерти».",
                    movieLength = "121",
                    ageRating = 6,
                    poster = "https://i.pinimg.com/474x/77/06/4d/77064dc550369ae49ee981788989b58b.jpg?nii=t",
                ),
            )
        )
    }
}

@Composable
@Preview
fun MovieScreenLoadingPreview() {
    TrovareTheme() {
        MovieDetailsScreenContent(
            onBackClick = {},
            currentState = MovieDetailsScreenViewModel.MovieDetailsScreenState.Loading
        )
    }
}