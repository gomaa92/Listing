package com.nagwa.listing.data.repository

import com.nagwa.listing.data.model.GetListOfFilesResponse
import com.nagwa.listing.data.remote.GetListOfFilesRemoteDataSource
import com.nagwa.listing.domain.repository.GetListOfFilesRepository
import io.reactivex.Observable
import io.reactivex.Observer
import javax.inject.Inject

class GetListOfFilesRepositoryImpl @Inject constructor(private val remoteDataSource: GetListOfFilesRemoteDataSource) :
    GetListOfFilesRepository {
    override  fun getListOfFiles(): Observable<List<GetListOfFilesResponse>> {
        return remoteDataSource.getListOfFiles()
    }
}