package com.example.viacep.data.repository

import com.example.viacep.data.api.ServiceApi
import com.example.viacep.data.model.AddressResponse
import com.example.viacep.domain.api.repository.AddressRepository
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi
) : AddressRepository {
    override suspend fun getAddress(cep: String): AddressResponse {
        return serviceApi.getAddress(cep)
    }
}