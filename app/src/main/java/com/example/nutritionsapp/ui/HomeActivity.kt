package com.example.nutritionsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.nutritionsapp.R
import com.example.nutritionsapp.data.DataManager
import com.example.nutritionsapp.databinding.ActivityHomeBinding
import com.example.nutritionsapp.util.Constants


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeFragment: HomeFragment
    private lateinit var searchFragment: SearchFragment
    private lateinit var calorieFragment : CalorieFragment
    val dataManager = DataManager()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val calories = intent.getIntExtra(Constants.CALORIES_KEY, 0)
        initSubView(calories)
        addNavigationListener()
    }

    private fun addNavigationListener() {
        binding.bottomAppBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.pageHome -> {
                    replaceFragment(homeFragment)
                    true
                }
                R.id.pageSearch -> {
                    searchFragment = SearchFragment.newInstance(dataManager)
                    replaceFragment(searchFragment)
                    true
                }
                R.id.pageCalorie -> {
                    calorieFragment = CalorieFragment.newInstance(dataManager)
                    replaceFragment(calorieFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun initSubView(calories: Int) {
        homeFragment = HomeFragment.newInstance(calories)
        addFragment(homeFragment)
    }

    fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, fragment)
        transaction.addToBackStack("fragment")
        transaction.commit()
    }

    fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}