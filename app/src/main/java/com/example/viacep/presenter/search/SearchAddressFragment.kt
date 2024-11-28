package com.example.viacep.presenter.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.viacep.R
import com.example.viacep.databinding.FragmentSearchAddressBinding
import com.example.viacep.domain.model.Address
import com.example.viacep.presenter.list.ListAddressViewModel
import com.example.viacep.util.StateView
import com.example.viacep.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchAddressFragment : Fragment() {
    private var address: Address? = null
    private val viewModel: SearchAddressViewModel by viewModels()
    private val ListAddressviewModel: ListAddressViewModel by activityViewModels()

    private var _binding: FragmentSearchAddressBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }
    private fun initListeners() {
        binding.editCep.addTextChangedListener {
            val text = it?.toString() ?: ""
            if(text.isNotEmpty()){
                if(text.length == 8){
                    hideKeyboard()
                    getAddress(cep = text)
                }
            }
        }
        binding.btnSave.setOnClickListener {
            if(address != null) {
                insertAddress(address!!)
            }else {
                findNavController().popBackStack()
            }

            findNavController().popBackStack()
        }
    }

    private fun getAddress(cep: String) {
        viewModel.getAddress(cep).observe(viewLifecycleOwner) {stateView ->
            when(stateView){
                is StateView.Loading -> {
                    binding.btnSave.isEnabled = false
                    binding.itemAddress.viewFlipper.displayedChild = 2
                }
                is StateView.Success -> {
                    if(stateView.data?.cep != null){
                        address = stateView.data

                        binding.btnSave.isEnabled = true
                        binding.itemAddress.viewFlipper.displayedChild = 1
                        binding.itemAddress.textAddress.text = stateView.data.getFullAddress()
                    } else {
                        stateError()
                    }
                }
                is StateView.Error -> {
                    stateError()
                }
            }
        }
    }

    private fun insertAddress(address: Address) {
        viewModel.insertAddress(address).observe(viewLifecycleOwner) { stateView ->
            when(stateView){
                is StateView.Loading -> {
                    ListAddressviewModel.addressChanged()
                }
                is StateView.Success -> {
                    ListAddressviewModel.addressChanged()
                    findNavController().popBackStack()
                }
                is StateView.Error -> {
                    Toast.makeText(requireContext(), "Erro ao salvar endereço!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun stateError(){
        address = null
        binding.btnSave.isEnabled = false
        binding.itemAddress.viewFlipper.displayedChild = 0
        binding.itemAddress.textEmptyAddress.text =
            getString(R.string.label_address_empty_search_address_fragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}