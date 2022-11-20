package com.example.todolist.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.todolist.R
import com.example.todolist.databinding.FragmentAddListBinding
import com.example.todolist.databinding.FragmentLoginBinding

class ListFragment : Fragment(R.layout.fragment_add_list) {
    private lateinit var binding: FragmentAddListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddListBinding.bind(view)
    }
}