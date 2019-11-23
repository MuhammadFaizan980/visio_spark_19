package com.squadtechs.markhor.visiospark19.activities

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.squadtechs.markhor.visiospark19.R
import com.squadtechs.markhor.visiospark19.fragments.FragmentDialed
import com.squadtechs.markhor.visiospark19.fragments.FragmentMissed
import com.squadtechs.markhor.visiospark19.fragments.FragmentReceived
import com.squadtechs.markhor.visiospark19.fragments.FragmentTalked


class MainActivity : AppCompatActivity() {
    private lateinit var txtDialed: TextView
    private lateinit var txtReceived: TextView
    private lateinit var txtTalked: TextView
    private lateinit var txtMissed: TextView
    private lateinit var calendar: CalendarView
    private lateinit var dialed: FragmentDialed
    private lateinit var received: FragmentReceived
    private lateinit var talked: FragmentTalked
    private lateinit var missed: FragmentMissed

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        startTransaction(dialed)
        // populateViewPager()
        setTabListeners()
        setCalendarListeners()
    }

    private fun setCalendarListeners() {

    }

    private fun setTabListeners() {
        txtDialed.setOnClickListener {
            txtDialed.textSize = 15.0f
            txtReceived.textSize = 14.0f
            txtTalked.textSize = 14.0f
            txtMissed.textSize = 14.0f
            txtDialed.setTextColor(Color.parseColor("#000000"))
            txtReceived.setTextColor(Color.parseColor("#90424242"))
            txtTalked.setTextColor(Color.parseColor("#90424242"))
            txtMissed.setTextColor(Color.parseColor("#90424242"))
            startTransaction(dialed)
        }
        txtReceived.setOnClickListener {
            txtDialed.textSize = 14.0f
            txtReceived.textSize = 15.0f
            txtTalked.textSize = 14.0f
            txtMissed.textSize = 14.0f
            txtDialed.setTextColor(Color.parseColor("#90424242"))
            txtReceived.setTextColor(Color.parseColor("#000000"))
            txtTalked.setTextColor(Color.parseColor("#90424242"))
            txtMissed.setTextColor(Color.parseColor("#90424242"))
            startTransaction(received)
        }
        txtTalked.setOnClickListener {
            txtDialed.textSize = 14.0f
            txtReceived.textSize = 14.0f
            txtTalked.textSize = 15.0f
            txtMissed.textSize = 14.0f
            txtDialed.setTextColor(Color.parseColor("#90424242"))
            txtReceived.setTextColor(Color.parseColor("#90424242"))
            txtTalked.setTextColor(Color.parseColor("#000000"))
            txtMissed.setTextColor(Color.parseColor("#90424242"))
            startTransaction(talked)
        }
        txtMissed.setOnClickListener {
            txtDialed.textSize = 14.0f
            txtReceived.textSize = 14.0f
            txtTalked.textSize = 14.0f
            txtMissed.textSize = 15.0f
            txtDialed.setTextColor(Color.parseColor("#90424242"))
            txtReceived.setTextColor(Color.parseColor("#90424242"))
            txtTalked.setTextColor(Color.parseColor("#90424242"))
            txtMissed.setTextColor(Color.parseColor("#000000"))
            startTransaction(missed)
        }
    }

//    private fun setViewPagerListener() {
//        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//            override fun onPageScrollStateChanged(state: Int) {}
//
//            override fun onPageScrolled(
//                position: Int,
//                positionOffset: Float,
//                positionOffsetPixels: Int
//            ) {
//            }
//
//            override fun onPageSelected(position: Int) {
//                when (position) {
//                    0 -> {
//                        txtDialed.textSize = 15.0f
//                        txtReceived.textSize = 14.0f
//                        txtTalked.textSize = 14.0f
//                        txtMissed.textSize = 14.0f
//                        txtDialed.setTextColor(Color.parseColor("#000000"))
//                        txtReceived.setTextColor(Color.parseColor("#90424242"))
//                        txtTalked.setTextColor(Color.parseColor("#90424242"))
//                        txtMissed.setTextColor(Color.parseColor("#90424242"))
//                    }
//                    1 -> {
//                        txtDialed.textSize = 14.0f
//                        txtReceived.textSize = 15.0f
//                        txtTalked.textSize = 14.0f
//                        txtMissed.textSize = 14.0f
//                        txtDialed.setTextColor(Color.parseColor("#90424242"))
//                        txtReceived.setTextColor(Color.parseColor("#000000"))
//                        txtTalked.setTextColor(Color.parseColor("#90424242"))
//                        txtMissed.setTextColor(Color.parseColor("#90424242"))
//                    }
//                    2 -> {
//                        txtDialed.textSize = 14.0f
//                        txtReceived.textSize = 14.0f
//                        txtTalked.textSize = 15.0f
//                        txtMissed.textSize = 14.0f
//                        txtDialed.setTextColor(Color.parseColor("#90424242"))
//                        txtReceived.setTextColor(Color.parseColor("#90424242"))
//                        txtTalked.setTextColor(Color.parseColor("#000000"))
//                        txtMissed.setTextColor(Color.parseColor("#90424242"))
//                    }
//                    3 -> {
//                        txtDialed.textSize = 14.0f
//                        txtReceived.textSize = 14.0f
//                        txtTalked.textSize = 14.0f
//                        txtMissed.textSize = 15.0f
//                        txtDialed.setTextColor(Color.parseColor("#90424242"))
//                        txtReceived.setTextColor(Color.parseColor("#90424242"))
//                        txtTalked.setTextColor(Color.parseColor("#90424242"))
//                        txtMissed.setTextColor(Color.parseColor("#000000"))
//                    }
//                }
//            }
//        })
//    }

    private fun initViews() {
        received = FragmentReceived()
        dialed = FragmentDialed()
        talked = FragmentTalked()
        missed = FragmentMissed()
        calendar = findViewById(R.id.calendar)
        //   viewPager = findViewById(R.id.viewpager)
        txtDialed = findViewById(R.id.txt_dialed)
        txtReceived = findViewById(R.id.txt_received)
        txtTalked = findViewById(R.id.txt_talked)
        txtMissed = findViewById(R.id.txt_missed)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun startTransaction(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.commit()
    }

}
