package com.kotlin.randomwoman.data.repository

import com.kotlin.randomwoman.data.remote.WomanApi
import com.kotlin.randomwoman.data.remote.dto.WomanDto
import com.kotlin.randomwoman.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WomanRepository @Inject constructor(private val api: WomanApi) {
    fun getWoman(): Flow<Resource<List<WomanDto>>> = flow {
        try{
            emit(Resource.Loading())

            val person = api.getWomen()

            emit(Resource.Success(person.results))
        }catch (e: HttpException){
            emit(Resource.Error(e.message ?: "Error HTTP"))

        }catch (e: IOException){
            emit(Resource.Error(e.message ?: "Verificar conexión"))
        }
    }
}