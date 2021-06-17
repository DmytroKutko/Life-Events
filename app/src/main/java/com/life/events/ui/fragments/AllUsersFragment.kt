package com.life.events.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.life.events.R
import com.life.events.adapters.UserAdapter
import com.life.events.entity.User
import com.life.events.utils.JsonConverter
import kotlinx.android.synthetic.main.fragment_all_users.*

class AllUsersFragment : Fragment(R.layout.fragment_all_users), AdapterView.OnItemSelectedListener {

    private lateinit var userAdapter: UserAdapter
    private val usersList = JsonConverter.getUsersList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initList()
        initSpinner()
    }

    private fun initSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.filter_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spFilter.adapter = adapter
        }
        spFilter.onItemSelectedListener = this
    }

    private fun initList() {
        userAdapter.submitList(usersList)
    }

    private fun initRecyclerView() = rvUsers.apply {
        userAdapter = UserAdapter()
        adapter = userAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun sortByPosition(position: Int) {
        when (position) {
            0 -> getAllUsers()
            1 -> getMaleUsers()
            2 -> getFemaleUsers()
            3 -> getAliveUsers()
            4 -> getDeadUsers()
        }
    }

    private fun getAllUsers() {
        userAdapter.submitList(usersList)
    }

    private fun getMaleUsers() {
        val sortedList = ArrayList<User>()
        usersList.forEach {
            if (it.gender == "male")
                sortedList.add(it)
        }
        userAdapter.submitList(sortedList)
    }

    private fun getFemaleUsers() {
        val sortedList = ArrayList<User>()
        usersList.forEach {
            if (it.gender == "female")
                sortedList.add(it)
        }
        userAdapter.submitList(sortedList)
    }

    private fun getAliveUsers() {
        val sortedList = ArrayList<User>()
        usersList.forEach {
            if (it.death == null)
                sortedList.add(it)
        }
        userAdapter.submitList(sortedList)
    }

    private fun getDeadUsers() {

        val sortedList = ArrayList<User>()
        usersList.forEach {
            if (it.death != null)
                sortedList.add(it)
        }
        userAdapter.submitList(sortedList)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        sortByPosition(position)
    }


    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}