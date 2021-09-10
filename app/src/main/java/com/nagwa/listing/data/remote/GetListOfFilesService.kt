package com.nagwa.listing.data.remote

import com.nagwa.listing.data.model.GetListOfFilesResponse
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.http.GET

interface GetListOfFilesService {
    @GET("movies")
     fun getListOfFiles(): Observable<List<GetListOfFilesResponse>>
}