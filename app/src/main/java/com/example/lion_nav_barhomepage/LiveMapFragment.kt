package com.example.lion_nav_barhomepage

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature
import com.example.lion_nav_barhomepage.databinding.FragmentBookVaccineBinding
import com.example.lion_nav_barhomepage.databinding.FragmentLiveMapBinding


class LiveMapFragment : Fragment() {
    private var _binding: FragmentLiveMapBinding? = null
    private val binding get() = _binding!!
    private lateinit var callback: OnBackPressedCallback


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.liveMapView.canGoBack()) {
                    binding.liveMapView.goBack()
                } else {
                    requireActivity().finish()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLiveMapBinding.inflate(inflater, container, false)
        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_YES -> {
                    WebSettingsCompat.setForceDark(
                        binding.liveMapView.settings,
                        WebSettingsCompat.FORCE_DARK_ON
                    )
                }
                Configuration.UI_MODE_NIGHT_NO, Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                    WebSettingsCompat.setForceDark(
                        binding.liveMapView.settings,
                        WebSettingsCompat.FORCE_DARK_OFF
                    )
                }
            }
        }

            val webSettings = binding.liveMapView.settings
            webSettings.javaScriptEnabled = true
            binding.liveMapView.webViewClient = WebViewClient()
            binding.progressBar.visibility=View.GONE
            binding.liveMapView.loadUrl("https://www.bing.com/covid")

        val view = binding.root
        return view
    }


}