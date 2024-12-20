package br.com.jrmantovani.rideexpress.presantation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.jrmantovani.rideexpress.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }

      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



    }
}