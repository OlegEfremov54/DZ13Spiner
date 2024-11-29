package com.example.dz13spiner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ListAdapter(context: Context, personList: MutableList<Person>): ArrayAdapter<Person>(context, R.layout.list_item, personList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val person = getItem(position)
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }
        val namelvTV = view?.findViewById<TextView>(R.id.nameListViewTV)
        val surNamelvTV = view?.findViewById<TextView>(R.id.surNameListViewTV)
        val rolelvTV = view?.findViewById<TextView>(R.id.roleListViewTV)
        val agelvTV = view?.findViewById<TextView>(R.id.ageListViewTV)

        namelvTV?.text = person?.name
        surNamelvTV?.text = person?.surName
        rolelvTV?.text = person?.role
        agelvTV?.text = person?.age
        return view!!
    }
}