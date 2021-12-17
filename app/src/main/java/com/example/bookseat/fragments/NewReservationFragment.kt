package com.example.bookseat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookseat.R
import com.example.bookseat.databinding.FragmentNewReservationBinding
import com.example.bookseat.repository.GoBackToProfile
import com.example.bookseat.repository.GoToChosenTime
import com.example.bookseat.viewmodels.NewReservationViewModel

class NewReservationFragment : Fragment() {

    private lateinit var binding: FragmentNewReservationBinding


    private val viewModel: NewReservationViewModel by lazy {
        ViewModelProvider(this).get(NewReservationViewModel::class.java)
    }


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