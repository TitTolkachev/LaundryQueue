package com.example.washingmachine.presentation.screens.onboarding

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.washingmachine.R
import com.example.washingmachine.databinding.ActivityOnboardingBinding
import com.example.washingmachine.presentation.screens.auth.AuthActivity
import com.example.washingmachine.presentation.screens.onboarding.adapters.ViewPagerAdapter
import com.example.washingmachine.presentation.screens.onboarding.model.PageData
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var adapter: ViewPagerAdapter
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        askNotificationPermission()

        adapter = ViewPagerAdapter(pages = getPages())
        val viewPager = binding.viewPager
        viewPager.adapter = adapter


        val viewPagerButton = binding.button
        viewPagerButton.setOnClickListener {
            when (viewPager.currentItem) {
                adapter.itemCount - 1 -> {
                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                else -> {
                    viewPager.setCurrentItem(viewPager.currentItem + 1, true)
                }
            }
        }

        val indicatorView = binding.indicatorView
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

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(this, "Notifications permission granted", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(
                this,
                "FCM can't post notifications without POST_NOTIFICATIONS permission",
                Toast.LENGTH_LONG,
            ).show()
        }
    }
}