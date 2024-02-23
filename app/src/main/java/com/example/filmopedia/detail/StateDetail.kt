package com.example.filmopedia.detail

import com.example.filmopedia.ListFilm.Domain.Model.Film


data class StateDetail(
    val isLoading: Boolean = false, //TODO: MENGECEK APAKAH LOADING, SECARA DEFAULT FALSE
    val movie: Film? = null //TODO: Nilai data film dalam tampilan detail, yang diambil dari Film.kt. Secara default, nilainya null/kosong
)
