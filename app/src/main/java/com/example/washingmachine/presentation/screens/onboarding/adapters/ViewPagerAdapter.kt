package com.example.washingmachine.presentation.screens.onboarding.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.washingmachine.databinding.ItemOnboardingViewPagerBinding
import com.example.washingmachine.presentation.screens.onboarding.model.PageData

class ViewPagerAdapter(
    private val pages: List<PageData> = listOf()
) : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {

    class PagerViewHolder(val binding: ItemOnboardingViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder =
        PagerViewHolder(
            ItemOnboardingViewPagerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = pages.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) = holder.itemView.run {
        holder.binding.imageView.setImageResource(pages[position].drawableResource)
        holder.binding.textView.text = pages[position].text
    }
}