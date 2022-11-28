package com.example.todolist.ui.register

import android.app.Notification.Action
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.core.Constants
import com.example.todolist.core.NetworkResult
import com.example.todolist.data.request.Register
import com.example.todolist.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar

class RegisterFragment : Fragment(R.layout.fragment_register) {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by lazy { MyViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)


        binding.apply {

            btRegister.setOnClickListener {
                val phone = etPhone.text.toString()
                val name = etName.text.toString()
                val password = etPassword.text.toString()

                val user = Register(phone, name, password)
                viewModel.register(user)

                viewModel.register.observe(viewLifecycleOwner) {
                    when (it) {
                        is NetworkResult.Loading -> {
                            setLoading(true)
                        }

                        is NetworkResult.Success -> {
                            setLoading(false)
                            Toast.makeText(
                                requireContext(),
                                "Register Successful",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            Constants.TOKEN = it.data?.token ?:""
                            Toast.makeText(requireContext(), "is successful", Toast.LENGTH_SHORT).show()

                            findNavController().navigate(R.id.action_registerFragment_to_listFragment)

                        }

                        is NetworkResult.Error -> {
                            setLoading(false)
                            Snackbar.make(btRegister, it.message.toString(), Snackbar.LENGTH_LONG)
                                .show()
                        }

                    }
                }
            }
        }
    }

    private fun setLoading(loading: Boolean){
        binding.progressBar.isVisible = loading
    }

}