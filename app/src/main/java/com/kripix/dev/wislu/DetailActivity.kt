package com.kripix.dev.wislu

import android.app.Person
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kripix.dev.wislu.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailBinding
    companion object {
        const val key_wisata = "key_wisata" // Declare the key constant
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.app_bar_name)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dataWisata = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Wisata>(key_wisata)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Wisata>(key_wisata)
        }

        dataWisata?.let { wisata ->
            binding.ivDestinationImage.setImageResource(wisata.photo1)
            binding.tvTitle.text = wisata.name
            binding.tvLocation.text = wisata.location
            binding.tvTicketPrice.text = wisata.ticket
            binding.tvOperationalHours.text = wisata.operational
            binding.tvDescription.text = wisata.description
        }

        binding.btnShare.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnShare -> {
                val dataWisata = if (Build.VERSION.SDK_INT >= 33) {
                    intent.getParcelableExtra<Wisata>(key_wisata)
                } else {
                    @Suppress("DEPRECATION")
                    intent.getParcelableExtra<Wisata>(key_wisata)
                }

                dataWisata?.let { wisata ->
                    val shareContent = """
                    Temukan destinasi wisata menarik berikut ini!
                    🌟 ${wisata.name}
                    📍 Lokasi: ${wisata.map}
                    💰 Harga Tiket: ${wisata.ticket}
                   
                    
                    Jangan lewatkan pengalaman serunya!
                """.trimIndent()

                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_SUBJECT, "Rekomendasi Wisata: ${wisata.name}")
                        putExtra(Intent.EXTRA_TEXT, shareContent)
                    }

                    startActivity(Intent.createChooser(shareIntent, "Bagikan wisata melalui"))
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> { // ID default untuk tombol back
                finish() // Menutup Activity saat tombol back ditekan
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}