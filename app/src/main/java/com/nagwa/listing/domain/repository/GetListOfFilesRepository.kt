package com.nagwa.listing.domain.repository

import com.nagwa.listing.data.model.GetListOfFilesResponse
import io.reactivex.Observable
import io.reactivex.Observer

interface GetListOfFilesRepository {
     fun getListOfFiles(): Observable<List<GetListOfFilesResponse>>

}