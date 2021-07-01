package com.example.busfahrer.ui

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import com.example.busfahrer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener{
            val players = ArrayList<String>()
            players.add(binding.player1.text.toString())
            players.add(binding.player2.text.toString())
            players.add(binding.player3.text.toString())
            players.add(binding.player4.text.toString())
            val intent = Intent(this, ChoiceActivity::class.java)
            intent.putExtra("PLAYERS", players)
            startActivity(intent)
        }
    }
}