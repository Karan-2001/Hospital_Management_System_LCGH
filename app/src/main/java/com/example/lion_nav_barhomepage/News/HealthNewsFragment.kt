package com.example.lion_nav_barhomepage.News

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

import com.example.lion_nav_barhomepage.R
import com.example.lion_nav_barhomepage.databinding.FragmentNewVaccinesBinding
import com.example.lion_nav_barhomepage.databinding.FragmentNewsBinding
import com.example.news.News

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HealthNewsFragment : Fragment(),NewsItemClicked{
    var Newsurl: String? = null
    private lateinit var recyclerView: RecyclerView
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: NewsListAdapter
    var count = 0
    var country = "in"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title="Health News"
        recyclerView = binding.recycle
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        fetchData()
        mAdapter = NewsListAdapter(this@HealthNewsFragment,this.requireContext())

        recyclerView.adapter = mAdapter


//        val countryList = arrayListOf<String>(
//            "INDIA",
//            "USA",
//            "RUSSIA",
//            "CHINA",
//            "New Zealand",
//            "UNITED ARAB EMIRTAS"
//        )
//        val adapter = ArrayAdapter(
//            this,
//            R.layout.row_layout, R.id.text, countryList
//        )
//        binding.spinner.adapter = adapter
//        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>,
//                view: View,
//                position: Int,
//                id: Long
//            ) {
//                count = position
//                if (position == null) {
//                    count = 0
//                }
////                // get selected item text
////                //Toast.makeText(this@MainActivity, "${parent.getItemAtPosition(position)}", Toast.LENGTH_SHORT).show()
//                if (count == 0) {
//                    country = "in"
//                    //Toast.makeText(this@MainActivity, "india", Toast.LENGTH_SHORT).show()
//                }
//                if (count == 1) {
//                    //Toast.makeText(this@MainActivity, "usa country", Toast.LENGTH_SHORT).show()
//                    country = "us"
//                }
//                if (count == 2) {
//                    country = "ru"
//                }
//                if (count == 3) {
//                    country = "ch"
//                }
//                if (count == 4) {
//                    country = "nz"
//                }
//                if (count == 5) {
//                    country = "ae"
//                }
//                fetchData()
////
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//
//
//        }
    }


private fun fetchData() {

    val url =
        "https://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=9caf9106ea20488581e20b523c1b2216"
    val jsonObjectRequest = object : JsonObjectRequest(
        Request.Method.GET,
        url,
        null,
        {
            val newsJsonArray = it.getJSONArray("articles")
            val newsArray = ArrayList<News>()
            for (i in 0 until newsJsonArray.length()) {
                val newsJsonObject = newsJsonArray.getJSONObject(i)
                val news = News(
                    newsJsonObject.getString("title"),
                    newsJsonObject.getString("author"),
                    newsJsonObject.getString("url"),
                    newsJsonObject.getString("urlToImage")
                )
                newsArray.add(news)
            }

            mAdapter.updateNews(newsArray)
        },
        {
            // Toast.makeText(this, "volly exception", Toast.LENGTH_SHORT).show()
        }
    ) {
        override fun getHeaders(): MutableMap<String, String> {
            val headers = HashMap<String, String>()
            headers["User-Agent"] = "Mozilla/5.0"
            return headers
        }
    }
    MySingleton.getInstance(requireContext()).addToRequestQueue(jsonObjectRequest)
}

override fun onItemClicked(item: News) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(requireContext(), Uri.parse(item.url))
}



}


