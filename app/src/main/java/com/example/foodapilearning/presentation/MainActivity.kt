package com.example.foodapilearning.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.foodapilearning.R
import com.example.foodapilearning.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_search ->{
                val getTextValue = binding.edtTxtSearch.text.toString()
                val moveWithDataIntent = Intent(this@MainActivity, ListMenuActivity::class.java)
                moveWithDataIntent.putExtra(ListMenuActivity.LOCATION_NAME, getTextValue)
                startActivity(moveWithDataIntent)
            }
        }
    }
}