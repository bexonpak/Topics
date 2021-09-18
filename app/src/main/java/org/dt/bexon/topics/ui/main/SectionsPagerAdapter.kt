package org.dt.bexon.topics.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.dt.bexon.topics.R
import org.dt.bexon.topics.model.Topics

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fm: FragmentManager, var topics: Topics) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(topics.data[position])
    }

    override fun getPageTitle(position: Int): CharSequence {
        return topics.data[position].name
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return topics.data.size
    }
}