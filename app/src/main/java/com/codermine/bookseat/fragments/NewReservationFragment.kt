package com.codermine.bookseat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codermine.bookseat.databinding.FragmentNewReservationBinding
import com.codermine.bookseat.repository.GoBackToProfile
import com.codermine.bookseat.repository.GoToChosenTime
import com.codermine.bookseat.viewmodels.NewReservationViewModel

class NewReservationFragment : Fragment() {

    private lateinit var binding: FragmentNewReservationBinding


    private val viewModel: NewReservationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewReservationBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.events.observe(viewLifecycleOwner) { event ->
            if (event != null) {
                when (event) {
                    is GoToChosenTime -> findNavController().navigate(
                        NewReservationFragmentDirections.actionNewReservationFragmentToPeriodFragment()
                    )
                    is GoBackToProfile -> findNavController().navigate(
                        NewReservationFragmentDirections.actionNewReservationFragmentToProfileFragment()
                    )
                }
                viewModel.eventCompleted()
            }
        }
    }

}