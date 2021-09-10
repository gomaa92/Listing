package com.nagwa.listing.di

import com.nagwa.listing.data.remote.GetListOfFilesService
import com.nagwa.listing.presentation.view.adapter.GetListOfFilesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(ViewModelComponent::class)
@Module
class APIModule {
    @Provides
    fun provideApi(retrofit: Retrofit): GetListOfFilesService {
        return retrofit.create(GetListOfFilesService::class.java)
    }

    @Provides
    @Singleton
    fun provideAdapter(): GetListOfFilesAdapter {
        return GetListOfFilesAdapter()
    }
}