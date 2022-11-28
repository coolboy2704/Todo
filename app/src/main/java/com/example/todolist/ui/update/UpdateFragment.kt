package com.example.todolist.ui.update

import androidx.fragment.app.Fragment
import com.example.todolist.R
import com.example.todolist.databinding.FragmentUpdateBinding
import com.example.todolist.ui.register.MyViewModel

class UpdateFragment : Fragment(R.layout.fragment_update) {
    private lateinit var binding: FragmentUpdateBinding
    private val viewModel by lazy { MyViewModel() }
}