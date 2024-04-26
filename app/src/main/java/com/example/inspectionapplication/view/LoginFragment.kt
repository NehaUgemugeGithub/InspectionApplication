package com.example.inspectionapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.inspectionapplication.R
import com.example.inspectionapplication.api.RetrofitInstance
import com.example.inspectionapplication.databinding.FragmentLoginBinding
import com.example.inspectionapplication.model.repository.InspectionRepository
import com.example.inspectionapplication.viewmodel.InspectionViewModel
import com.example.inspectionapplication.viewmodel.ViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: InspectionViewModel
    var isAllFieldsChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        val repository = InspectionRepository(RetrofitInstance.api)
        viewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(InspectionViewModel::class.java)

        setupListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle back button press
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
    }

    private fun setupListener(){
        binding.btnLogin.setOnClickListener {
            val userName = binding.editTextUsername
            val password = binding.editTextPassword
            isAllFieldsChecked = checkAllFields()

            if (isAllFieldsChecked) {
                performLogin(userName.toString(),password.toString())
            }
        }

        binding.textViewSignUp.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }
    }

    private fun performLogin(username: String, password: String) {
        viewModel.login(username, password,
            onSuccess = {
                // Handle successful login
                Toast.makeText(requireContext(),"Login Successful", Toast.LENGTH_SHORT).show()
//                findNavController().navigate(R.id.inspectionFragment)
            },
            onError = {
                // Handle login error
                Toast.makeText(requireContext()," invalid username or password", Toast.LENGTH_SHORT).show()
            }
        )
        findNavController().navigate(R.id.inspectionFragment)
    }

    private fun checkAllFields(): Boolean {
        if (binding.edtUsername.length() == 0) {
            binding.edtUsername.error = "Username is required"
            return false
        }
        if (binding.edtPwd.length() == 0) {
            binding.edtPwd.error = "Password is required"
            return false
        } else if (binding.edtPwd.length() < 8) {
            binding.edtPwd.error = "Password must be minimum 8 characters"
            return false
        }

        // after all validation return true.
        return true
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}