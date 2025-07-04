package com.example.visitus

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager

class RecyclerActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecyclerBinding
    lateinit var adapter: AddapterRe
    var Noteslist= arrayListOf<Re>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityRecyclerBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setContentView(R.layout.activity_recycler)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
            Noteslist.add(Re(title = "My title", description = "My description"))
            Noteslist.add(Re(title = "My title", description = "My description"))
            Noteslist.add(Re(title = "My title", description = "My description"))
            AddapterRe=AddapterRe(Noteslist)
            binding.recycler.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
            binding.recycler.adapter=AddapterRe
           AddapterRe.notifyDataSetChanged()

    }
}