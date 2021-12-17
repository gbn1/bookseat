package com.example.bookseat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import com.example.bookseat.databinding.FragmentPeriodBinding
import com.example.bookseat.viewmodels.PeriodViewModel
import android.widget.ArrayAdapter


class PeriodFragment : Fragment() {

    private val periodViewModel: PeriodViewModel by viewModels()

    private lateinit var binding: FragmentPeriodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPeriodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupListeners()
        setupObservers()

        periodViewModel.fetchRoomsNames()
    }

    private fun setupViews() {
        // setup adapter spinner
        binding.roomsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // TODO("Not yet implemented")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }

    private fun setupListeners() {
        // setup spinner on item selected

        // onItemSelected chiamata di rete per recuperare info stanza (lista sedie)
    }

    private fun setupObservers() {
        periodViewModel.rooms.observe(viewLifecycleOwner) { roomList ->
            // fill spinner adapter

            val spinnerArrayAdapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                roomList.map { room -> room.roomsId }
            )

            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.roomsSpinner.adapter = spinnerArrayAdapter

        }

        // add observer lista sedie
        // dentro observer lista sedie popolare view con lista sedie
    }
}