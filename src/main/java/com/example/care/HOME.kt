package com.example.care

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.care.databinding.HomeActivityBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.home_activity.*

class HOME : AppCompatActivity() {

    private lateinit var binding: HomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btm_nav.setOnNavigationItemSelectedListener(itm_slct)
        replacefragment(Navigation ())
        setSupportActionBar(tool_bar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
         val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
             this,
             drawer_layout,
             tool_bar,
             R.string.Drawer_Open,
             R.string.Drawer_Close
         ){
             override fun onDrawerClosed(drawerView: View) {
                 super.onDrawerClosed(drawerView)
                 setTitle(R.string.app_name)
             }

             override fun onDrawerOpened(drawerView: View) {
                 super.onDrawerOpened(drawerView)
                 setTitle(R.string.Drawer)
             }
         }
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        drawer_nav.setNavigationItemSelectedListener {
            item: MenuItem ->
            when(item.itemId){
                R.id.Feedback->{
                    LoadFeedback(Feedback())
                    true
                }
                R.id.Report_prblm->{
                    LoadReport(Report_problem())
                    true
                }
                R.id.faqs->{
                    LoadFAQs(FAQs())
                    true
                }
                R.id.contactus->{
                    LoadContactus(Contact_us())
                    true
                }
                else-> super.onOptionsItemSelected(item)
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }




    }


    val itm_slct = BottomNavigationView.OnNavigationItemSelectedListener{
            menuItem ->
        when(menuItem.itemId){
            R.id.ic_nav->{
                replacefragment(Navigation())
                return@OnNavigationItemSelectedListener true
            }
            R.id.ic_Search->{
                replacefragment(Search())
                return@OnNavigationItemSelectedListener true
            }
            R.id.ic_QR->{
                replacefragment(QR())
                return@OnNavigationItemSelectedListener true
            }
            R.id.ic_Profile->{
                replacefragment(Profile())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    fun replacefragment (frag: Fragment){
        val ft : FragmentTransaction =supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,frag)
        ft.commit()
    }

    fun LoadReport (frag1 : Report_problem ){
        val ft1 : FragmentTransaction =supportFragmentManager.beginTransaction()
        ft1.replace(R.id.fragment,frag1)
        ft1.commit()
    }
    fun LoadFeedback (frag2 : Feedback ){
        val ft1 : FragmentTransaction =supportFragmentManager.beginTransaction()
        ft1.replace(R.id.fragment,frag2)
        ft1.commit()
    }
    fun LoadContactus (frag3 : Contact_us ){
        val ft1 : FragmentTransaction =supportFragmentManager.beginTransaction()
        ft1.replace(R.id.fragment,frag3)
        ft1.commit()
    }
    fun LoadFAQs (frag4 : FAQs ){
        val ft1 : FragmentTransaction =supportFragmentManager.beginTransaction()
        ft1.replace(R.id.fragment,frag4)
        ft1.commit()
    }
}