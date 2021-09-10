package com.nagwa.listing.domain.usecase

import com.nagwa.listing.data.model.GetListOfFilesResponse
import com.nagwa.listing.domain.repository.GetListOfFilesRepository
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.Observable
import io.reactivex.Observer
import javax.inject.Inject

@ViewModelScoped
class GetListOfFilesUseCase @Inject constructor(private val repository: GetListOfFilesRepository) :
    SuspendableUseCase<Unit, Observable<List<GetListOfFilesResponse>>> {
    override  fun execute(input: Unit?): Observable<List<GetListOfFilesResponse>> {
        return repository.getListOfFiles()
    }

}