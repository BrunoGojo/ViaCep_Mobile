package com.example.viacep.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.viacep.data.local.entity.AddressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDao {

    @Query("SELECT * FROM address")
    fun getAllAddress(): Flow<List<AddressEntity>>

    @Query("SELECT * FROM address WHERE id = :id")
    fun getAddressById(id: Long): AddressEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAddress(addressEntity: AddressEntity): Long

    @Update
    fun updateAddress(addressEntity: AddressEntity)

    @Delete
    fun deleteAddress(addressEntity: AddressEntity)

}