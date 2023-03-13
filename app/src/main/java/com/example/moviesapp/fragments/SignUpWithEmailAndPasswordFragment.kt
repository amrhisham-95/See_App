package com.example.moviesapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.moviesapp.R
import com.example.moviesapp.activities.ContentActivity
import com.example.moviesapp.databinding.FragmentSignUpWithEmailAndPasswordBinding
import com.example.moviesapp.activities.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpWithEmailAndPasswordFragment : Fragment() {

private lateinit var binding : FragmentSignUpWithEmailAndPasswordBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up_with_email_and_password,container,false)
        binding.lifecycleOwner = this

        //to put title on toolbar
        (activity as MainActivity).supportActionBar?.title = getString(R.string.signUp_fragment)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        addMenu()

        binding.signUpBtn.setOnClickListener {
            if (binding.editTextEmailAddressSignUp.text.isEmpty() || binding.editTextPasswordSignUp.text.isEmpty()) {
                Toast.makeText(requireContext(), "Please Enter The Empty Square", Toast.LENGTH_LONG)
                    .show()
            } else {
                auth.createUserWithEmailAndPassword(
                    binding.editTextEmailAddressSignUp.text.toString().trim(),
                    binding.editTextPasswordSignUp.text.toString().trim()
                )
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(
                                requireContext(),
                                "signUpWithEmail:success",
                                Toast.LENGTH_LONG
                            )
                                .show()
                            val user = auth.currentUser
                            val intent = Intent(activity, ContentActivity::class.java)
                            startActivity(intent)
                            updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                requireContext(),
                                "signUpWithEmail:failure",
                                Toast.LENGTH_LONG
                            )
                                .show()
                            updateUI(null)
                        }
                    }

            }
        }


    }

    private fun updateUI(currentUser: FirebaseUser?) {

    }

    private fun addMenu(){
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu1, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.back-> {
                        findNavController().navigate(R.id.action_signUpWithEmailAndPasswordFragment_to_loginFragment)
                        //to put title on toolbar
                        (activity as MainActivity).supportActionBar?.title = "See (Movies & TV)"
                        return true
                    }
                    else -> {
                        false
                    }
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

}