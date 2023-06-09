package com.example.washingmachine.presentation.screens.onboarding

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.example.washingmachine.R
import com.example.washingmachine.presentation.screens.onboarding.adapters.ViewPagerAdapter
import com.example.washingmachine.presentation.screens.onboarding.model.PageData
import com.zhpan.indicator.IndicatorView
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ViewPagerAdapter(pages = getPages())
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = adapter

        val indicatorView = findViewById<IndicatorView>(R.id.indicatorView)
        indicatorView.apply {
            setSliderColor(Color.BLUE, Color.BLACK)
            setSliderWidth(
                resources.getDimension(R.dimen.default_6dp),
                resources.getDimension(R.dimen.default_16dp)
            )
            setSliderHeight(resources.getDimension(R.dimen.default_6dp))
            setSlideMode(IndicatorSlideMode.SCALE)
            setIndicatorStyle(IndicatorStyle.ROUND_RECT)
            setPageSize(viewPager.adapter!!.itemCount)
            notifyDataChanged()
        }
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                indicatorView.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                indicatorView.onPageSelected(position)

                val viewPagerButton = findViewById<Button>(R.id.button)
                viewPagerButton.text = position.toString()
            }
        })
    }

    private fun getPages(): List<PageData> {
        return listOf(
            PageData(
                drawableResource = R.drawable.ic_launcher_foreground,
                title = "Title 1",
                text = "Text 1"
            ),
            PageData(
                drawableResource = R.drawable.ic_launcher_background,
                title = "Title 2",
                text = "Text 2"
            ),
            PageData(
                drawableResource = R.drawable.ic_launcher_foreground,
                title = "Title 3",
                text = "Text 3"
            )
        )
    }
}