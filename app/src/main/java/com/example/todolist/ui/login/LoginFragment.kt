package com.example.todolist.ui.login


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.core.Constants.TOKEN
import com.example.todolist.core.NetworkResult
import com.example.todolist.core.loginRequest
import com.example.todolist.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginFragment: Fragment(R.layout.fragment_login)  {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var navController: NavController
    private val viewModel by lazy { LoginViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        navController = findNavController()

        binding.apply {

            binding.bnRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }

            bnLogin.setOnClickListener {
                val number = etNumber.text.toString()
                val password = etPassword.text.toString()

                val loginUser = loginRequest(number, password)

                viewModel.login(loginUser)

                viewModel.login.observe(viewLifecycleOwner) {
                    when (it) {
                        is NetworkResult.Loading -> {
                            setLoading(true)
                        }

                        is NetworkResult.Success -> {
                            setLoading(false)
                            Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                            TOKEN = it.data?.token ?: ""
                            navController.navigate(R.id.action_registerFragment_to_listFragment)
                        }

                        is NetworkResult.Error -> {
                            setLoading(false)
                            Snackbar.make(bnLogin, it.message.toString(), Snackbar.LENGTH_SHORT).show()
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