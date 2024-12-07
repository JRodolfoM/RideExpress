package br.com.jrmantovani.rideexpress

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.jrmantovani.rideexpress.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}