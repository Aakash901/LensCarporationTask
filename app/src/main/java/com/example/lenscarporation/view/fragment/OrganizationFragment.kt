package com.example.lenscarporation.view.fragment

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.lenscarporation.R
import java.io.IOException
import java.io.InputStream

class OrganizationFragment : Fragment() {

    private lateinit var highlightedText: TextView
    private lateinit var constitutionBtn: TextView
    private lateinit var incumbencyBtn: TextView
    private lateinit var executiveBtn: TextView
    private lateinit var constituentBtn: TextView
    private lateinit var header_: TextView
    private lateinit var header_1: TextView
    private lateinit var header_2: TextView
    private lateinit var innerHeader2: TextView
    private lateinit var innerHeader1: TextView
    private lateinit var innerHeader: TextView
    private lateinit var constitutionv: View
    private lateinit var incumbencyv: View
    private lateinit var executivev: View
    private lateinit var constituentv: View
    private lateinit var downloadPdf1: LinearLayout
    private lateinit var downloadPdf2: LinearLayout
    private lateinit var downloadPdf3: LinearLayout
    private lateinit var downloadPdf4: LinearLayout
    private lateinit var downloadPdf5: LinearLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_organization, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        downloadPdf1 = view.findViewById(R.id.downloadPdf1)
        downloadPdf2 = view.findViewById(R.id.downloadPdf2)
        downloadPdf3 = view.findViewById(R.id.downloadPdf3)
        downloadPdf4 = view.findViewById(R.id.downloadPdf4)
        downloadPdf5 = view.findViewById(R.id.downloadPdf5)

        constitutionv = view.findViewById(R.id.constitutionv)
        incumbencyv = view.findViewById(R.id.incumbencyv)
        executivev = view.findViewById(R.id.executivev)
        constituentv = view.findViewById(R.id.constituentv)

        highlightedText = view.findViewById(R.id.highlightedText)
        constitutionBtn = view.findViewById(R.id.constitutionBtn)
        incumbencyBtn = view.findViewById(R.id.incumbencyBtn)
        executiveBtn = view.findViewById(R.id.executiveBtn)
        constituentBtn = view.findViewById(R.id.constituentBtn)
        header_ = view.findViewById(R.id.header_)
        header_1 = view.findViewById(R.id.header_1)
        header_2 = view.findViewById(R.id.header_2)
        innerHeader2 = view.findViewById(R.id.innerHeader2)
        innerHeader1 = view.findViewById(R.id.innerHeader1)
        innerHeader = view.findViewById(R.id.innerHeader)

        view.findViewById<CardView>(R.id.constitution).setOnClickListener {
            handleCardViewClick(CardViewType.CONSTITUTION, constitutionv)
            highlightedText.text = constitutionBtn.text

        }

        view.findViewById<CardView>(R.id.executive).setOnClickListener {
            handleCardViewClick(CardViewType.EXECUTIVE, executivev)
            highlightedText.text = executiveBtn.text
            header_.text = executiveBtn.text
            innerHeader2.text = executiveBtn.text
        }

        view.findViewById<CardView>(R.id.constituent).setOnClickListener {
            handleCardViewClick(CardViewType.CONSTITUENT, constituentv)
            highlightedText.text = constituentBtn.text
            header_1.text = constituentBtn.text
            innerHeader1.text = constituentBtn.text
        }

        view.findViewById<CardView>(R.id.incumbency).setOnClickListener {
            handleCardViewClick(CardViewType.INCUMBENCY, incumbencyv)
            highlightedText.text = incumbencyBtn.text
            header_2.text = incumbencyBtn.text
            innerHeader2.text = incumbencyBtn.text
        }


        handleCardViewClick(CardViewType.CONSTITUTION, constitutionv)
        highlightedText.text = constitutionBtn.text

        downloadPdf1.setOnClickListener { downloadPdfFromUrl("https://firebasestorage.googleapis.com/v0/b/video-45f46.appspot.com/o/pdf%2Ftesting%20(1).pdf%3A1716669210121.pdf?alt=media&token=7d47e3df-3d1d-456a-b3f6-1ea820ae088f") }
        downloadPdf2.setOnClickListener { downloadPdfFromUrl("https://firebasestorage.googleapis.com/v0/b/video-45f46.appspot.com/o/pdf%2Ftesting%20(1).pdf%3A1716669210121.pdf?alt=media&token=7d47e3df-3d1d-456a-b3f6-1ea820ae088f") }
        downloadPdf3.setOnClickListener { downloadPdfFromUrl("https://firebasestorage.googleapis.com/v0/b/video-45f46.appspot.com/o/pdf%2Ftesting%20(1).pdf%3A1716669210121.pdf?alt=media&token=7d47e3df-3d1d-456a-b3f6-1ea820ae088f") }
        downloadPdf4.setOnClickListener { downloadPdfFromUrl("https://firebasestorage.googleapis.com/v0/b/video-45f46.appspot.com/o/pdf%2Ftesting%20(1).pdf%3A1716669210121.pdf?alt=media&token=7d47e3df-3d1d-456a-b3f6-1ea820ae088f") }
        downloadPdf5.setOnClickListener { downloadPdfFromUrl("https://firebasestorage.googleapis.com/v0/b/video-45f46.appspot.com/o/pdf%2Ftesting%20(1).pdf%3A1716669210121.pdf?alt=media&token=7d47e3df-3d1d-456a-b3f6-1ea820ae088f") }

    }


    private fun downloadPdfFromUrl(url: String) {
        val fileName = url.substring(url.lastIndexOf('/') + 1)
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle(fileName)
            .setDescription("Downloading PDF...")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)

        val downloadManager =
            requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadId = downloadManager.enqueue(request)

        val onComplete = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (downloadId == intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)) {
                    Toast.makeText(requireContext(), "Download Completed", Toast.LENGTH_SHORT)
                        .show()
                    val downloadedFile = downloadManager.getUriForDownloadedFile(downloadId)
                    val filePath = downloadedFile?.path
                    Toast.makeText(requireContext(), "File path: $filePath", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

        requireContext().registerReceiver(
            onComplete,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),
            ContextCompat.RECEIVER_NOT_EXPORTED
        )
    }


    private fun handleCardViewClick(cardViewType: CardViewType, selectedView: View) {
        val constitutionLL = requireView().findViewById<LinearLayout>(R.id.constitutionLL)
        val executiveLL = requireView().findViewById<RelativeLayout>(R.id.executiveLL)
        val constituentUnitLL = requireView().findViewById<RelativeLayout>(R.id.constituentUnitLL)
        val incumbencyLL = requireView().findViewById<RelativeLayout>(R.id.incumbencyLL)

        // Hide all layouts initially
        constitutionLL.visibility = View.GONE
        executiveLL.visibility = View.GONE
        constituentUnitLL.visibility = View.GONE
        incumbencyLL.visibility = View.GONE
        constitutionv.visibility = View.GONE
        executivev.visibility = View.GONE
        constituentv.visibility = View.GONE
        incumbencyv.visibility = View.GONE

        // Show the desired layout based on the clicked CardView
        when (cardViewType) {
            CardViewType.CONSTITUTION -> {
                constitutionLL.visibility = View.VISIBLE
                selectedView.visibility = View.VISIBLE
            }

            CardViewType.EXECUTIVE -> {
                executiveLL.visibility = View.VISIBLE
                selectedView.visibility = View.VISIBLE
            }

            CardViewType.CONSTITUENT -> {
                constituentUnitLL.visibility = View.VISIBLE
                selectedView.visibility = View.VISIBLE
            }

            CardViewType.INCUMBENCY -> {
                incumbencyLL.visibility = View.VISIBLE
                selectedView.visibility = View.VISIBLE
            }
        }
    }

    private enum class CardViewType {
        CONSTITUTION, EXECUTIVE, CONSTITUENT, INCUMBENCY
    }
}
