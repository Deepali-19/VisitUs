package com.example.visitus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView




class AddapterRe (var list :ArrayList<Re>): RecyclerView.Adapter<AddapterRe.ViewHolder>() {
    class ViewHolder (var view: View):RecyclerView.ViewHolder(view){

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddapterRe.ViewHolder {
        TODO("Not yet implemented")
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_re,parent,false))
        return ViewHolder(view)    }

    override fun onBindViewHolder(holder: AddapterRe.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}
//,var clickInterface: OnClick
) {
}