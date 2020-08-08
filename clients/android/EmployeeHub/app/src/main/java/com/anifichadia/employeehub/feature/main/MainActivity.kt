package com.anifichadia.employeehub.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anifichadia.employeehub.databinding.ActivityMainBinding

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
