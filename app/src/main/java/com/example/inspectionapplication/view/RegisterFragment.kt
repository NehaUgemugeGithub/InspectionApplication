package com.example.inspectionapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.inspectionapplication.R
import com.example.inspectionapplication.databinding.FragmentLoginBinding
import com.example.inspectionapplication.databinding.FragmentRegisterBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
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
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        setupListener()
        return binding.root
    }

    private fun setupListener(){
        binding.btnRegister.setOnClickListener {
            isAllFieldsChecked = checkAllFields()

            if (isAllFieldsChecked) {
                Toast.makeText(requireContext(), "Registration Successful", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(R.id.loginFragment)
            }
        }
    }

    private fun checkAllFields(): Boolean {
        if (binding.editTextUsername.length() == 0) {
            binding.editTextUsername.error = "Username is required"
            return false
        }
        if (binding.editTextPwd.length() == 0) {
            binding.editTextPwd.error = "Password is required"
            return false
        } else if (binding.editTextPwd.length() < 8) {
            binding.editTextPwd.error = "Password must be minimum 8 characters"
            return false
        }

        // after all validation return true.
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle back button press
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}