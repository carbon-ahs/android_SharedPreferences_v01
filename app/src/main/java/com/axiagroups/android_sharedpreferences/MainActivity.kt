package com.axiagroups.android_sharedpreferences

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.axiagroups.android_sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding //add here_
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val pref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = pref.edit()

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val age = binding.etAge.text.toString().toInt()
            val isAdult = binding.cbAdult.isChecked

            editor.apply {
                putString("name", name)
                putInt("age", age)
                putBoolean("isAdult", isAdult)
                apply()
            }

        }

        binding.btnClear.setOnClickListener {
            binding.etName.setText("")
            binding.etAge.setText("")
            binding.cbAdult.isChecked = false
        }

        binding.btnLoad.setOnClickListener {
            binding.etName.setText(pref.getString("name", "Name"))
            binding.etAge.setText(pref.getInt("age", 18).toString())
            binding.cbAdult.isChecked = pref.getBoolean("isAdult", true)
        }
    }
}