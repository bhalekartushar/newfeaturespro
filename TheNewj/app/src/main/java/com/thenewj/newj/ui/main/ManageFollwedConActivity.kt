package com.thenewj.newj.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.thenewj.newj.R
import com.thenewj.newj.databinding.ActivityMainBinding
import com.thenewj.newj.databinding.ActivityManagefollowedcontentBinding
import com.thenewj.newj.ui.adapter.ManageContentPagerAdapter
import com.thenewj.newj.ui.main.category.CategoryFragment
import com.thenewj.newj.ui.main.event.EventFragment
import com.thenewj.newj.ui.main.interest.InterestFragment

class ManageFollwedConActivity: AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityManagefollowedcontentBinding


     lateinit var viewPager: ViewPager
     lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManagefollowedcontentBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setSupportActionBar(binding.viewManagetoolbar.toolbar)

        var back = findViewById<ImageView>(R.id.arrowback)

        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        initViews()
        setStatepagerAdpter()

        tabLayout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener
        {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager.currentItem = tab.position
                    val fm = supportFragmentManager
                    val ft = fm.beginTransaction()
                    val count = fm.backStackEntryCount
                    if (count>=1)
                    {
                        supportFragmentManager.popBackStack()
                    }
                    ft.commit()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun initViews() {
        viewPager = findViewById<ViewPager>(R.id.viewpager_contents)
        tabLayout = findViewById<TabLayout>(R.id.tab_contents)
    }

    private fun setStatepagerAdpter() {
        val manageContentPagerAdapter: ManageContentPagerAdapter = ManageContentPagerAdapter(supportFragmentManager)
        manageContentPagerAdapter.addFragment(EventFragment(), "Events")
        manageContentPagerAdapter.addFragment(CategoryFragment(), "Categories")
        manageContentPagerAdapter.addFragment(InterestFragment(), "Interests")

        viewPager.adapter = manageContentPagerAdapter
//        tabLayout.setupWithViewPager(viewPager, true)
        tabLayout.setupWithViewPager(viewPager, true)
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}