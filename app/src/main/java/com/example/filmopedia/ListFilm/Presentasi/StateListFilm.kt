package com.example.filmopedia.ListFilm.Presentasi

import com.example.filmopedia.ListFilm.Domain.Model.Film

data class StateListFilm( //TODO: Class yang mengatur state default halaman list
    val isLoading: Boolean = false, //TODO: Secara default, loading tidak dimulai dan hanya berjalan setelah user memasuki suatu halaman

    val popularMovieListPage: Int = 1, //TODO: Hlaman default saat masuk app yaitu halaman 1
    val upcomingMovieListPage: Int = 1,

    val isCurrentPopularScreen: Boolean = true, //TODO: Halaman default saat membuka app adalah Halaman 'Popular'

    val popularMovieList: List<Film> = emptyList(),//TODO: Secara default, list film kosong, dan diambil dari domain Film.kt
    val upcomingMovieList: List<Film> = emptyList()
)