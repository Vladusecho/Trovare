package com.vladusecho.trovare.presentation.screen

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.vladusecho.trovare.R
import com.vladusecho.trovare.domain.model.Movie
import com.vladusecho.trovare.domain.model.MovieRating
import com.vladusecho.trovare.presentation.element.HomeMovieCard
import com.vladusecho.trovare.presentation.ui.theme.TrovareTheme
import com.vladusecho.trovare.presentation.ui.theme.TrovareTypography
import com.vladusecho.trovare.presentation.viewModel.HomeScreenViewModel
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onSearchClick: () -> Unit,
    onMovieClick: (Int) -> Unit
) {

    val state = viewModel.state.collectAsState()
    val currentState = state.value

    HomeScreenContent(
        currentState = currentState,
        onSearchClick = onSearchClick,
        onMovieClick = onMovieClick
    )
}

@Composable
fun HomeScreenContent(
    currentState: HomeScreenViewModel.HomeScreenState,
    onSearchClick: () -> Unit,
    onMovieClick: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xff979797).copy(alpha = 0.1f))
                .height(300.dp),
        ) {

        }
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 64.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_movie),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "ФИЛЬМЫ",
                    color = Color.Gray,
                    fontFamily = TrovareTypography,
                    fontWeight = FontWeight.Black,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Что вы хотите\nпосмотреть сегодня?",
                    color = Color.Black,
                    fontFamily = TrovareTypography,
                    fontWeight = FontWeight.Black,
                    fontSize = 24.sp,
                )
                IconButton(onClick = onSearchClick) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                        contentDescription = null,
                        tint = Color.Black,
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            when (currentState) {
                is HomeScreenViewModel.HomeScreenState.Content -> {
                    val pagerState = rememberPagerState(pageCount = { currentState.movies.size })
                    HorizontalPager(
                        state = pagerState,
                        contentPadding = PaddingValues(horizontal = 50.dp),
                        beyondViewportPageCount = 1
                    ) { page ->
                        val movie = currentState.movies[page]
                        HomeMovieCard(
                            movie = movie,
                            modifier = Modifier.graphicsLayer {
                                val pageOffset = (
                                        (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                                        ).absoluteValue
                                alpha = lerp(
                                    start = 0.5f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                                scaleY = lerp(
                                    start = 0.8f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                            },
                            onCardClick = { onMovieClick(movie.id) }
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        PagerIndicator(
                            count = currentState.movies.size,
                            currentPage = pagerState.currentPage,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                }

                is HomeScreenViewModel.HomeScreenState.Error -> {

                }

                is HomeScreenViewModel.HomeScreenState.Initial -> {

                }

                is HomeScreenViewModel.HomeScreenState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PagerIndicator(
    count: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(count) { iteration ->
            val color = if (currentPage == iteration) Color.Black else Color.LightGray
            val width = if (currentPage == iteration) 12.dp else 6.dp

            Box(
                modifier = Modifier
                    .height(6.dp)
                    .width(width)
                    .background(color, shape = RoundedCornerShape(3.dp))
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
fun HomeScreenContentPreview() {
    TrovareTheme {
        HomeScreenContent(
            currentState = HomeScreenViewModel.HomeScreenState.Content(
                movies = listOf(
                    Movie(
                        id = 0,
                        name = "Звёздные войны: Эпизод 4 – Новая надежда",
                        year = 1977,
                        description = "Татуин. Планета-пустыня. Уже постаревший рыцарь Джедай Оби Ван Кеноби спасает молодого Люка Скайуокера, когда тот пытается отыскать пропавшего дроида. С этого момента Люк осознает свое истинное назначение: он один из рыцарей Джедай. В то время как гражданская война охватила галактику, а войска повстанцев ведут бои против сил злого Императора, к Люку и Оби Вану присоединяется отчаянный пилот-наемник Хан Соло, и в сопровождении двух дроидов, R2D2 и C-3PO, этот необычный отряд отправляется на поиски предводителя повстанцев – принцессы Леи. Героям предстоит отчаянная схватка с устрашающим Дартом Вейдером – правой рукой Императора и его секретным оружием – «Звездой Смерти».",
                        movieLength = "121",
                        ageRating = 6,
                        poster = "https://i.pinimg.com/474x/77/06/4d/77064dc550369ae49ee981788989b58b.jpg?nii=t",
                        genres = listOf("Боевик", "Фэнтези", "Приключения", "Драма"),
                        rating = MovieRating(
                            kp = "8.2",
                            imdb = "8.2"
                        )
                    ),
                    Movie(
                        id = 1,
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
                    ),
                    Movie(
                        id = 2,
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
                )
            ),
            onSearchClick = {},
            onMovieClick = {}
        )
    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
fun HomeScreenLoadingPreview() {
    TrovareTheme {
        HomeScreenContent(
            currentState = HomeScreenViewModel.HomeScreenState.Loading,
            onSearchClick = {},
            onMovieClick = {}
        )
    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
fun HomeScreenErrorPreview() {
    TrovareTheme {
        HomeScreenContent(
            currentState = HomeScreenViewModel.HomeScreenState.Error,
            onSearchClick = {},
            onMovieClick = {}
        )
    }
}

