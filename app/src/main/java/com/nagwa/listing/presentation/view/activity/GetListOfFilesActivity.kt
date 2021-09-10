package com.nagwa.listing.presentation.view.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.nagwa.listing.R
import com.nagwa.listing.data.model.GetListOfFilesResponse
import com.nagwa.listing.presentation.view.adapter.GetListOfFilesAdapter
import com.nagwa.listing.presentation.viewmodel.GetListOfFilesViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


@AndroidEntryPoint
class GetListOfFilesActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: GetListOfFilesAdapter
    private val TAG = "GetListOfFilesActivity"
    private val viewModel: GetListOfFilesViewModel by viewModels()
    private var mCompositeDisposable: CompositeDisposable? = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        viewModel.getListOfFiles()
        bindScreenData()
        bindLoading()

    }

    private fun bindLoading() {
        addDisposable(
            viewModel.getLoadingSubject()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::loadingStatus)
        )
    }

    private fun loadingStatus(status: Boolean) {
        progressBar.visibility = if (status) View.VISIBLE else View.GONE

    }

    private fun bindScreenData() {
        addDisposable(
            viewModel.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateUI)
        )
    }

    private fun updateUI(data: List<GetListOfFilesResponse>) {
        Log.d(TAG, "updateUI: $data")
        adapter.setItems(data)

    }

    private fun initRecyclerView() {
        recyclerView.adapter = adapter
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


    override fun onDestroy() {
        disposeDisposable()
        super.onDestroy()

    }
}