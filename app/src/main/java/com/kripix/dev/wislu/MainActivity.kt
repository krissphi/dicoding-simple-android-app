package com.kripix.dev.wislu

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.kripix.dev.wislu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Wisata>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.title = getString(R.string.app_bar_name)

        binding.rvWisata.setHasFixedSize(true)
        list.addAll(getListWisata())
        showRecyclerList()
    }

    private fun getListWisata(): ArrayList<Wisata>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataLocation = resources.getStringArray(R.array.data_location)
        val dataMap = resources.getStringArray(R.array.data_map)
        val dataOperational = resources.getStringArray(R.array.data_operational)
        val dataTicket = resources.getStringArray(R.array.data_ticket)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto1 = resources.obtainTypedArray(R.array.data_photo_1)
        val listWisata = ArrayList<Wisata>()
        for (i in dataName.indices){
            val wisata = Wisata(dataName[i], dataLocation[i], dataMap[i], dataOperational[i], dataTicket[i], dataDescription[i], dataPhoto1.getResourceId(i, -1))
            listWisata.add(wisata)
        }
        return listWisata
    }

    private fun showRecyclerList(){
        binding.rvWisata.layoutManager = LinearLayoutManager(this)
        val listWisataAdapter = ListWisataAdapter(list)
        binding.rvWisata.adapter = listWisataAdapter


        listWisataAdapter.setOnItemClickCallback(object : ListWisataAdapter.OnItemClickCallback{
            override fun onItemClick(data: Wisata) {
                showSelectedWisata(data)
            }
        })
    }

    private fun showSelectedWisata(wisata: Wisata){
        Toast.makeText(this, "Kamu memilih " + wisata.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}