package com.example.viacep.presenter.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.viacep.R
import com.example.viacep.databinding.FragmentListAddressBinding
import com.example.viacep.presenter.list.adapter.AddressAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListAddressFragment : Fragment() {

    private val viewModel: ListAddressViewModel by activityViewModels()

    private var _binding: FragmentListAddressBinding? = null
    private val binding get() = _binding!!

    private lateinit var addressAdapter: AddressAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        initObservers()

        initListeners()
    }

    private fun initListeners() {
        binding.fabAdd.setOnClickListener{
            findNavController().navigate(R.id.action_listAddressFragment_to_searchAddressFragment)
        }
    }

    private fun initObservers(){
        viewModel.currentAddressList.observe(viewLifecycleOwner) { addresses ->
            addressAdapter.submitList(addresses)
        }

        viewModel.addressChanged.observe(viewLifecycleOwner){
            viewModel.getAllAddress()
        }
    }

    private fun initRecycler() {
        addressAdapter = AddressAdapter()
        with(binding.recyclerAddress) {
            adapter = addressAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}