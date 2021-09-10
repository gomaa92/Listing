package com.nagwa.listing.data.remote

import com.nagwa.listing.data.model.GetListOfFilesResponse
import io.reactivex.Observable
import io.reactivex.Observer
import javax.inject.Inject

class GetListOfFilesRemoteDataSourceImpl @Inject constructor(private val service: GetListOfFilesService) :
    GetListOfFilesRemoteDataSource {
    override  fun getListOfFiles(): Observable<List<GetListOfFilesResponse>> {
        return service.getListOfFiles()
    }
}