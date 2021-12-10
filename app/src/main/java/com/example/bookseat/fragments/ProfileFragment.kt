package com.example.bookseat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookseat.R
import com.example.bookseat.databinding.FragmentProfileBinding
import com.example.bookseat.repository.GoToManageReservation
import com.example.bookseat.repository.GoToNewReservation
import com.example.bookseat.viewmodels.ProfileViewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel: ProfileViewModel by lazy {
        ViewModelProvider(this).get(ProfileViewModel::class.java)
    }

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
                    is GoToManageReservation -> findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToManageReservationFragment())
                }

                viewModel.eventCompleted()
            }
        }
    }

}