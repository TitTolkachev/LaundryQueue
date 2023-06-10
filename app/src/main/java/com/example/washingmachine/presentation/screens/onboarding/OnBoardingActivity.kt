package com.example.washingmachine.presentation.screens.onboarding

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
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


        val viewPagerButton = findViewById<Button>(R.id.button)
        viewPagerButton.setOnClickListener {
            when (viewPager.currentItem) {
                adapter.itemCount - 1 -> {
                    // TODO
                }

                else -> {
                    viewPager.setCurrentItem(viewPager.currentItem + 1, true)
                }
            }
        }

        val indicatorView = findViewById<IndicatorView>(R.id.indicatorView)
        indicatorView.apply {
            setSliderColor(Color.WHITE, Color.BLACK)
            setSliderWidth(
                resources.getDimension(R.dimen.default_10dp),
                resources.getDimension(R.dimen.default_24dp)
            )
            setSliderHeight(resources.getDimension(R.dimen.default_10dp))
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

                viewPagerButton.text = when (position) {
                    adapter.itemCount - 1 -> {
                        getString(R.string.onboarding_button_text_2)
                    }

                    else -> {
                        getString(R.string.onboarding_button_text_1)
                    }
                }
            }
        })
    }

    private fun getPages(): List<PageData> {
        return listOf(
            PageData(
                drawableResource = R.drawable.image_wm_1,
                text = getString(R.string.onboarding_text_1)
            ),
            PageData(
                drawableResource = R.drawable.image_wm_2,
                text = getString(R.string.onboarding_text_2)
            ),
            PageData(
                drawableResource = R.drawable.image_wm_3,
                text = getString(R.string.onboarding_text_3)
            )
        )
    }
}