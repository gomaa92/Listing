package com.nagwa.listing.presentation.view.adapter

import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import com.nagwa.listing.R
import com.nagwa.listing.data.model.GetListOfFilesResponse
import kotlinx.android.synthetic.main.item_pdf.view.*
import kotlinx.android.synthetic.main.item_vedio.view.*
import javax.inject.Inject
import kotlin.random.Random

class GetListOfFilesAdapter @Inject constructor() :
    BaseRecyclerViewAdapter<GetListOfFilesResponse, BaseRecyclerViewAdapter.BaseRecyclerViewHolder<GetListOfFilesResponse>>(

    ) {
    enum class Types {
        PDF, VIDEO
    }

    enum class TypesValues(name: String) {
        VIDEO("VIDEO")
    }

    override fun getItemViewType(position: Int): Int {
        return if (getData()?.get(position)?.type == TypesValues.VIDEO.name) {
            Types.VIDEO.ordinal
        } else {
            Types.PDF.ordinal
        }
    }

    override fun getLayout(type: Int): Int {
        return when (type) {
            Types.PDF.ordinal -> R.layout.item_pdf
            Types.VIDEO.ordinal -> R.layout.item_vedio
            else -> R.layout.item_pdf
        }
    }

    override fun getViewHolder(
        view: View,
        type: Int
    ): BaseRecyclerViewHolder<GetListOfFilesResponse> {
        return when (type) {
            Types.PDF.ordinal -> ListPdfViewHolder(view)
            Types.VIDEO.ordinal -> ListVideoViewHolder(view)
            else -> ListPdfViewHolder(view)
        }
    }

    inner class ListPdfViewHolder(itemView: View) :
        BaseRecyclerViewAdapter.BaseRecyclerViewHolder<GetListOfFilesResponse>(itemView) {

        override fun onBind(item: GetListOfFilesResponse) {
            itemView.pdfTitle.text = item.name
            itemView.downloadPdf.setOnClickListener {
                startDownload(itemView.pdf_progress_bar, itemView.pdfPercentage)
                itemView.downloadPdf.isEnabled = false
            }
        }
    }

    inner class ListVideoViewHolder(itemView: View) :
        BaseRecyclerViewAdapter.BaseRecyclerViewHolder<GetListOfFilesResponse>(itemView) {

        override fun onBind(item: GetListOfFilesResponse) {
            var position = 0
            itemView.videoTitle.text = item.name
            itemView.downloadVideo.setOnClickListener {
                startDownload(itemView.video_progress_bar, itemView.videoPercentage)
                itemView.downloadVideo.isEnabled = false

            }
            itemView.videoView.setOnClickListener {
                if (itemView.videoView.isPlaying) {
                    position = itemView.videoView.currentPosition
                    itemView.videoView.pause()
                } else if (!itemView.videoView.isPlaying && !itemView.playVideo.isVisible) {
                    itemView.videoView.seekTo(position)
                    itemView.videoView.start()
                }
            }
            itemView.playVideo.setOnClickListener {
                itemView.videoView.setVideoPath(item.url)
                itemView.videoView.start()
                itemView.playVideo.visibility = View.GONE
            }
            itemView.videoView.setOnCompletionListener {
                itemView.playVideo.visibility = View.VISIBLE
            }

        }
    }

    private fun startDownload(progressBar: ProgressBar, textView: TextView) {
        progressBar.visibility = View.VISIBLE
        var progressStatus = 0
        val handler = Handler()
        progressBar.progress = 0
        val filesToDownload = Random.nextInt(10, 200)
        progressBar.max = filesToDownload
        Thread {
            while (progressStatus < filesToDownload) {
                progressStatus += 1
                Thread.sleep(50)
                handler.post {
                    progressBar.progress = progressStatus
                    val percentage = ((progressStatus.toDouble()
                            / filesToDownload) * 100).toInt()
                    textView.text = "Downloaded $percentage%"
                }
            }
        }.start()


    }


}