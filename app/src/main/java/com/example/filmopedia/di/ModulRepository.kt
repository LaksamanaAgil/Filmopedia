package com.example.filmopedia.di

import com.example.filmopedia.ListFilm.Data.Repository.ImplRepositoryListFilm
import com.example.filmopedia.ListFilm.Domain.Repository.RepositoryListFilm
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class ModulRepository { //TODO: Kelas Modul untuk binding Repository dengan implementasinya

    @Binds
    @Singleton
    abstract fun bindMovieListRepository(
        movieListRepositoryImpl: ImplRepositoryListFilm //TODO: Menetapkan file kotlin yang bertugas sebagai implementasi Repository
    ): RepositoryListFilm //TODO: Binding Implementasi Repository dengan file Repository Aplikasi Film

}

