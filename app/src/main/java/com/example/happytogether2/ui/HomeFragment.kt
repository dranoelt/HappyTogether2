package com.example.happytogether2.ui

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.happytogether2.Login.EXTRA_DATA
import com.example.happytogether2.MainActivity
import com.example.happytogether2.R
import com.example.happytogether2.databinding.HomeFragmentBinding
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private var mInterAds : InterstitialAd? = null
    private var mRewardVid : RewardedAd?=null
    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: FragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        MobileAds.initialize(this.context) {
            binding.adView.loadAd(AdRequest.Builder().build())

            binding.adView.adListener = object : AdListener() {

            }
            binding.button2.visibility = View.GONE
            binding.button3.visibility = View.GONE
            InterstitialAd.load(this.context,"ca-app-pub-3940256099942544/1033173712",AdRequest.Builder().build(),object : InterstitialAdLoadCallback(){
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    Toast.makeText(this@HomeFragment.context, "load failed",Toast.LENGTH_SHORT).show()

                    mInterAds = null
                }

                override fun onAdLoaded(p0: InterstitialAd) {
                    super.onAdLoaded(p0)
                    mInterAds = p0
                    binding.button3.visibility = View.VISIBLE
                }
            })
            // rewarded ad
            RewardedAd.load(this.context,"ca-app-pub-3940256099942544/5224354917",AdRequest.Builder().build(),object : RewardedAdLoadCallback(){
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    Toast.makeText(this@HomeFragment.context,"Load falied",Toast.LENGTH_SHORT).show()
                    mRewardVid = null
                }

                override fun onAdLoaded(p0: RewardedAd) {
                    super.onAdLoaded(p0)
                    mRewardVid = p0
                    binding.button2.visibility = View.VISIBLE
                }
            })
            binding.button3.setOnClickListener {

                    if(mInterAds!=null){
                        mInterAds?.show(this.activity)
                    }


            }
            binding.button2.setOnClickListener{


                    var type = "coin "
                    var amount = 0
                    if (mRewardVid!=null){
                        mRewardVid?.show(this.activity, OnUserEarnedRewardListener {
                            amount = 10000
                            binding.textView2.setText("$type = $amount")
                        })
                    }


            }
        }



        return view

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(FragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }


}


