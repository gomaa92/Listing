package com.nagwa.listing.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.nagwa.listing.data.model.GetListOfFilesResponse
import com.nagwa.listing.domain.usecase.GetListOfFilesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject


@HiltViewModel
class GetListOfFilesViewModel @Inject constructor(private val useCase: GetListOfFilesUseCase) :
    ViewModel() {
    private var data: BehaviorSubject<List<GetListOfFilesResponse>> = BehaviorSubject.create()
    private var mLoadingSubject: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)
    private var mCompositeDisposable: CompositeDisposable? = CompositeDisposable()


    fun getListOfFiles() {
        mLoadingSubject.onNext(true)
        addDisposable(
            useCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doFinally { mLoadingSubject.onNext(false) }
                .subscribe {
                    data.onNext(it)
                }
        )
    }

     fun getData() = data

    fun getLoadingSubject(): BehaviorSubject<Boolean> {
        return mLoadingSubject
    }

    private fun addDisposable(disposable: Disposable?) {
        check(!(disposable == null && mCompositeDisposable == null)) { " Disposable must be initialized" }
        mCompositeDisposable?.add(disposable!!)
    }

    private fun disposeDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable!!.dispose()
        }
    }

    override fun onCleared() {
        mLoadingSubject.onComplete()
        data.onComplete()
        disposeDisposable()
        super.onCleared()
    }

}