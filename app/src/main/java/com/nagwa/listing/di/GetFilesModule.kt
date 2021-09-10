package com.nagwa.listing.di

import com.nagwa.listing.data.remote.GetListOfFilesRemoteDataSource
import com.nagwa.listing.data.remote.GetListOfFilesRemoteDataSourceImpl
import com.nagwa.listing.data.repository.GetListOfFilesRepositoryImpl
import com.nagwa.listing.domain.repository.GetListOfFilesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class GetFilesModule {
    @Binds
    abstract fun provideRepository(repository: GetListOfFilesRepositoryImpl): GetListOfFilesRepository

    @Binds
    abstract fun provideRemoteDataSourceModel(
        remoteDataSource: GetListOfFilesRemoteDataSourceImpl
    ): GetListOfFilesRemoteDataSource
}