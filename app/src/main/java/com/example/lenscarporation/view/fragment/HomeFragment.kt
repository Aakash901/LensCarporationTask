package com.example.lenscarporation.view.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.lenscarporation.utils.ImageSliderAdapter
import com.example.lenscarporation.R


class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var imageSliderAdapter: ImageSliderAdapter

    private lateinit var irpofLL: LinearLayout
    private lateinit var missionVisionLL: LinearLayout
    private lateinit var recentEventsLL: LinearLayout
    private lateinit var imageLL: LinearLayout
    private lateinit var videosLL: LinearLayout

    private lateinit var irpofv: View
    private lateinit var missionVisionv: View
    private lateinit var recentEventsv: View
    private lateinit var imagev: View
    private lateinit var videosv: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val irpofCard = view.findViewById<CardView>(R.id.irpof)
        val missionVisionCard = view.findViewById<CardView>(R.id.missionVision)
        val recentEventsCard = view.findViewById<CardView>(R.id.recentEvents)
        val imageCard = view.findViewById<CardView>(R.id.image)
        val videosCard = view.findViewById<CardView>(R.id.videos)

        irpofLL = view.findViewById(R.id.irpofLL)
        missionVisionLL = view.findViewById(R.id.missionVisionLL)
        recentEventsLL = view.findViewById(R.id.recentEventsLL)
        imageLL = view.findViewById(R.id.imageLL)
        videosLL = view.findViewById(R.id.videosLL)

        irpofv = view.findViewById(R.id.irpofv)
        missionVisionv = view.findViewById(R.id.missionVisionv)
        recentEventsv = view.findViewById(R.id.recentEventsv)
        imagev= view.findViewById(R.id.imagev)
        videosv = view.findViewById(R.id.videosv)

        showSelectedLayout(irpofLL,irpofv)

        irpofCard.setOnClickListener { showSelectedLayout(irpofLL,irpofv) }
        missionVisionCard.setOnClickListener { showSelectedLayout(missionVisionLL,missionVisionv) }
        recentEventsCard.setOnClickListener { showSelectedLayout(recentEventsLL,recentEventsv) }
        imageCard.setOnClickListener { showSelectedLayout(imageLL,imagev) }
        videosCard.setOnClickListener { showSelectedLayout(videosLL,videosv) }

        recyclerView = view.findViewById(R.id.recyclerView)

        val images = listOf(
            R.drawable.homeimage1,
            R.drawable.homeimage2,
            R.drawable.homeimage3,

        )

        imageSliderAdapter = ImageSliderAdapter(requireContext(), images)
        recyclerView.adapter = imageSliderAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        val highlightedText = view.findViewById<TextView>(R.id.highlightedText)
        highlightedText.isSelected = true
        highlightedText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        highlightedText.marqueeRepeatLimit
        return view
    }
    private fun showSelectedLayout(selectedLayout: LinearLayout,selectedView :View) {

        irpofLL.visibility = View.GONE
        missionVisionLL.visibility = View.GONE
        recentEventsLL.visibility = View.GONE
        imageLL.visibility = View.GONE
        videosLL.visibility = View.GONE
        irpofv.visibility = View.GONE
        missionVisionv.visibility = View.GONE
        recentEventsv.visibility = View.GONE
        imagev.visibility = View.GONE
        videosv.visibility = View.GONE


        selectedLayout.visibility = View.VISIBLE
        selectedView.visibility = View.VISIBLE
    }
}

