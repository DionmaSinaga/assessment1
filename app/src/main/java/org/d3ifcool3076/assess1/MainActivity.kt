package org.d3ifcool3076.assess1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import org.d3ifcool3076.assess1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var fAdapter: FilmAdapter
    private lateinit var list: ArrayList<Film>

    @SuppressLint("NotifyDataChanged", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = ArrayList()
        list.addAll(initItems())
        fAdapter = FilmAdapter(list)

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = fAdapter
        }
        fAdapter.run { notifyDataSetChanged() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)

        val menuItem = menu.findItem(R.id.search_action)
        val searchView = menuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }
        })

        return true
    }

    private fun filter(text: String) {
        val filteredList = arrayListOf<Film>()

        for (item in list) {
            if (item.name.lowercase().contains(text.lowercase())){
                filteredList.add(item)
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No Data Found!", Toast.LENGTH_SHORT).show()
        } else {
            fAdapter.filtering(filteredList)
        }
    }



    private fun initItems(): ArrayList<Film> {
        return arrayListOf(
            Film("Infinity War", R.drawable.infinity_war),
            Film("Dilan 1990", R.drawable.Dilan),
            Film("Haikyuu!", R.drawable.Haikyuu),
            Film("Itaewon Class", R.drawable.Itaewon_class),
            Film("Descendant of The Sun", R.drawable.dots),
        )
    }
}