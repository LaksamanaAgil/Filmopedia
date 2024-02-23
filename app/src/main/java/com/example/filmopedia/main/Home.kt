package com.example.filmopedia.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Upcoming
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.filmopedia.R
import com.example.filmopedia.ListFilm.Presentasi.Populer
import com.example.filmopedia.ListFilm.Presentasi.UIEventListFilm
import com.example.filmopedia.ListFilm.Presentasi.Upcoming
import com.example.filmopedia.ListFilm.Presentasi.ViewModelListFilm
import com.example.filmopedia.ListFilm.Util.Screen

//TODO: File ini mengatur halaman utama/halaman home dari app Filmopedia
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController) {

    val movieListViewModel = hiltViewModel<ViewModelListFilm>() //TPDP: Menetapkan ViewModelListFilm sebagai VM
    val movieListState = movieListViewModel.movieListState.collectAsState().value //TODO: Mengkoleksi data dari view model kedalam state
    val bottomNavController = rememberNavController()

    Scaffold(bottomBar = {
        BottomNavigationBar(
            bottomNavController = bottomNavController, onEvent = movieListViewModel::onEvent
        )
    }, topBar = { //TODO: Pengaturan Top AppBar
        TopAppBar(
            title = { //TODO: Judul di appbar sesuai dengan halaman populer / upcoming
                Text(
                    text = if (movieListState.isCurrentPopularScreen)
                        stringResource(R.string.popular_movies)
                    else
                        stringResource(R.string.upcoming_movies),
                    fontSize = 20.sp
                )
            },
            modifier = Modifier.shadow(2.dp),
            colors = TopAppBarDefaults.smallTopAppBarColors(
                MaterialTheme.colorScheme.inverseOnSurface
            )
        )
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            NavHost(
                navController = bottomNavController,
                startDestination = Screen.PopularMovieList.rout
            ) {
                composable(Screen.PopularMovieList.rout) {
                    Populer(
                        navController = navController,
                        movieListState = movieListState,
                        onEvent = movieListViewModel::onEvent
                    )
                }
                composable(Screen.UpcomingMovieList.rout) {
                    Upcoming(
                        navController = navController,
                        movieListState = movieListState,
                        onEvent = movieListViewModel::onEvent
                    )
                }
            }
        }
    }

}


@Composable
fun BottomNavigationBar(
    bottomNavController: NavHostController, onEvent: (UIEventListFilm) -> Unit
) {

    val items = listOf(
        BottomItem(
            title = stringResource(R.string.popular),
            icon = Icons.Rounded.Movie
        ), BottomItem(
            title = stringResource(R.string.upcoming),
            icon = Icons.Rounded.Upcoming
        )
    )

    val selected = rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            items.forEachIndexed { index, bottomItem ->
                NavigationBarItem(selected = selected.intValue == index, onClick = {
                    selected.intValue = index
                    when (selected.intValue) {
                        0 -> {
                            onEvent(UIEventListFilm.Navigate)
                            bottomNavController.popBackStack()
                            bottomNavController.navigate(Screen.PopularMovieList.rout)
                        }

                        1 -> {
                            onEvent(UIEventListFilm.Navigate)
                            bottomNavController.popBackStack()
                            bottomNavController.navigate(Screen.UpcomingMovieList.rout)
                        }
                    }
                }, icon = {
                    Icon(
                        imageVector = bottomItem.icon,
                        contentDescription = bottomItem.title,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }, label = {
                    Text(
                        text = bottomItem.title, color = MaterialTheme.colorScheme.onBackground
                    )
                })
            }
        }
    }

}

data class BottomItem(
    val title: String, val icon: ImageVector
)











