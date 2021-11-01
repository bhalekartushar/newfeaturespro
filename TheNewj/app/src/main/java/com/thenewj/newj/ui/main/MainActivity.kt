package com.thenewj.newj.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.ui.AppBarConfiguration
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomsheet.BottomSheetDialog
//import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
//import com.google.firebase.messaging.FirebaseMessaging
import com.thenewj.newj.R
import com.thenewj.newj.databinding.ActivityMainBinding
import com.thenewj.newj.ui.main.explore.ExploreFragment
import com.thenewj.newj.ui.main.myfeed.MyFeedFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG: String = "MainActivity"
        private const val TOPIC: String = "TheNewj"
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRegisteredToken()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.toolbar.tabLayout.setupWithViewPager(binding.vp)
        setupViewPager()
        initListeners()
    }

    private fun initListeners() {
        binding.toolbar.ivThreeDots.setOnClickListener {
//            Snackbar.make(
//                binding.toolbar.ivThreeDots,
//                "Work in progress...",
//                Snackbar.LENGTH_SHORT
//            ).show()

            getShowAlert();
        }
        binding.vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    binding.toolbar.tvTab1.background =
                        ContextCompat.getDrawable(this@MainActivity, R.drawable.shape_rectangle_tab_selected)
                    binding.toolbar.tvTab2.background = null
                    binding.toolbar.tvTab1.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.text_color_selected))
                    binding.toolbar.tvTab2.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.text_color_default))
                } else {
                    binding.toolbar.tvTab2.background =
                        ContextCompat.getDrawable(this@MainActivity, R.drawable.shape_rectangle_tab_selected)
                    binding.toolbar.tvTab1.background = null
                    binding.toolbar.tvTab2.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.text_color_selected))
                    binding.toolbar.tvTab1.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.text_color_default))
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
        binding.toolbar.tvTab1.setOnClickListener {
            binding.vp.currentItem = 0
        }
        binding.toolbar.tvTab2.setOnClickListener {
            binding.vp.currentItem = 1
        }
    }

    private fun getShowAlert() {
        val dialog = BottomSheetDialog(this@MainActivity)
        dialog.setContentView(R.layout.moremenu_dialoguebox)


        var managelinear = dialog.findViewById<LinearLayout>(R.id.linear_managecon)
        var cancellinear = dialog.findViewById<LinearLayout>(R.id.linear_cancel)

        if (managelinear != null) {
            managelinear.setOnClickListener {

                val intent = Intent(this, ManageFollowedConActivity::class.java)
                startActivity(intent)

            }
        }


        dialog.show()
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MyFeedFragment(), getString(R.string.my_feed))
        adapter.addFragment(ExploreFragment(), getString(R.string.explore))
        binding.vp.adapter = adapter
    }

    class ViewPagerAdapter(supportFragmentManager: FragmentManager) : FragmentPagerAdapter(supportFragmentManager) {

        private var fragmentList1: ArrayList<Fragment> = ArrayList()
        private var fragmentTitleList1: ArrayList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return fragmentList1[position]
        }

        @Nullable
        override fun getPageTitle(position: Int): CharSequence {
            return fragmentTitleList1[position]
        }

        override fun getCount(): Int {
            return fragmentList1.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList1.add(fragment)
            fragmentTitleList1.add(title)
        }
    }

    private fun initRegisteredToken() {
        /* FirebaseMessaging.getInstance().isAutoInitEnabled = true
         FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
         MyFirebaseMessagingService.sharedPref = getSharedPreferences("SM_SHARED_PREF", Context.MODE_PRIVATE)
         FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
             if (!task.isSuccessful) {
                 Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                 return@OnCompleteListener
             }
             MyFirebaseMessagingService.token = task.result
             Log.i(TAG, "RegisteredToken:${MyFirebaseMessagingService.token}")
         })*/
    }

}