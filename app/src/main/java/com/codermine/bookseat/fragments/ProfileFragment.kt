package com.codermine.bookseat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codermine.bookseat.R
import com.codermine.bookseat.databinding.FragmentProfileBinding
import com.codermine.bookseat.repository.GoToManageReservation
import com.codermine.bookseat.repository.GoToNewReservation
import com.codermine.bookseat.viewmodels.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = FragmentProfileBinding.inflate(layoutInflater)
        //binding.root

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.events.observe(viewLifecycleOwner) { event ->
            if (null != event) {
                when (event) {
                    is GoToNewReservation -> findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToNewReservationFragment())
                    is GoToManageReservation -> findNavController().navigate(
                        ProfileFragmentDirections.actionProfileFragmentToManageReservationFragment()
                    )
                }

                viewModel.eventCompleted()
            }
        }
        auth = FirebaseAuth.getInstance()
        accountEmail()

    }

    private fun accountEmail() {
        auth.currentUser?.email?.let {
            val title = getString(R.string.title_profile, it)
            binding.titleProfile.text = title
        }
    }

}