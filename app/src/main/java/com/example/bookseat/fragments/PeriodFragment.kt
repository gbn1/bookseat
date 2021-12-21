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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookseat.fragments.adapters.DeskAdapter
import com.example.bookseat.repository.Room


class PeriodFragment : Fragment() {

    private val periodViewModel: PeriodViewModel by viewModels()

    private lateinit var binding: FragmentPeriodBinding

    lateinit var spinnerArrayAdapter: ArrayAdapter<Room>

    lateinit var recyclerView: RecyclerView


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

        recyclerView = binding.recyclerViewGridlayout
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

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
        binding.roomsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val room = spinnerArrayAdapter.getItem(position)
                recyclerView.adapter = DeskAdapter(periodViewModel.fetchData(room))
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                /* NOOP */
            }

        }

    }

    private fun setupObservers() {
        periodViewModel.rooms.observe(viewLifecycleOwner) { rooms ->

            spinnerArrayAdapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                rooms
            )

            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            binding.roomsSpinner.adapter = spinnerArrayAdapter


        }
    }


}