package com.nagwa.listing.data.remote

import com.nagwa.listing.data.model.GetListOfFilesResponse
import io.reactivex.Observable

interface GetListOfFilesRemoteDataSource {
    fun getListOfFiles(): Observable<List<GetListOfFilesResponse>>

}