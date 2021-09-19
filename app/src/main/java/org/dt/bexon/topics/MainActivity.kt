package org.dt.bexon.topics

import android.os.Bundle
import android.webkit.WebSettings
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import okhttp3.OkHttpClient
import okhttp3.Request
import org.dt.bexon.topics.databinding.ActivityMainBinding
import org.dt.bexon.topics.model.Topics
import org.dt.bexon.topics.network.TopicsService
import org.dt.bexon.topics.ui.main.SectionsPagerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var topics: Topics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
    }

    private fun notifyDataSetChanged() {
        runOnUiThread {
            val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, topics)
            val viewPager: ViewPager = binding.viewPager
            viewPager.adapter = sectionsPagerAdapter
            val tabs: TabLayout = binding.tabs
            tabs.tabMode = TabLayout.MODE_SCROLLABLE
            tabs.setupWithViewPager(viewPager)
        }
    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://momoyu.cc/")
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val topicsService = retrofit.create(TopicsService::class.java)
        Thread {
            topicsService.getList(0).enqueue(object : Callback<Topics> {
                override fun onResponse(call: Call<Topics>, response: Response<Topics>) {
                    if (response.body() != null) {
                        topics = response.body()!!
                        notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<Topics>, t: Throwable) {
                }

            })
        }.start()

    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request: Request = chain.request()
                    .newBuilder()
                    .removeHeader("User-Agent") //移除旧的
                    .addHeader(
                        "User-Agent",
                        WebSettings.getDefaultUserAgent(this)
                    ) //添加真正的头部
                    .build()
                chain.proceed(request)
            }.build()
    }

}