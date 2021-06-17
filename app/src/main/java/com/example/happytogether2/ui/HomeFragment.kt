package com.example.happytogether2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.happytogether2.databinding.HomeFragmentBinding
import com.google.android.gms.ads.*
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
        MobileAds.initialize(this.requireContext()) {
            binding.adView.loadAd(AdRequest.Builder().build())

            binding.adView.adListener = object : AdListener() {

            }
            binding.button2.visibility = View.GONE
            binding.button3.visibility = View.GONE
            InterstitialAd.load(this.requireContext(),"ca-app-pub-3940256099942544/1033173712",AdRequest.Builder().build(),object : InterstitialAdLoadCallback(){
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
            RewardedAd.load(this.requireContext(),"ca-app-pub-3940256099942544/5224354917",AdRequest.Builder().build(),object : RewardedAdLoadCallback(){
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
                        mInterAds?.show(this.requireActivity())
                    }


            }
            binding.button2.setOnClickListener{


                    var type = "coin "
                    var amount = 0
                    if (mRewardVid!=null){
                        mRewardVid?.show(this.requireActivity(), OnUserEarnedRewardListener {
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


