package org.skyfaced.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.skyfaced.data.model.Character
import org.skyfaced.data.model.InfoWrapper
import org.skyfaced.data.model.asModel
import org.skyfaced.data.model.wrap
import org.skyfaced.network.RMNetworkDataSource
import org.skyfaced.network.model.CharacterDto
import org.skyfaced.network.model.filter.CharacterFilter
import javax.inject.Inject

// TODO Replace hardcoded dispatcher
class CharacterRepositoryImpl @Inject constructor(
    private val network: RMNetworkDataSource
) : CharacterRepository {
    override fun getPagedCharactersFlow(
        page: Int,
        filter: CharacterFilter
    ): Flow<InfoWrapper<Character>> = flow {
        val response = network.getAllCharacters(page, filter)
        val info = response.info.asModel()
        val characters = response.data.map(CharacterDto::asModel)
        val result = info wrap characters
        emit(result)
    }.flowOn(Dispatchers.IO)

    override fun getSingleCharacterFlow(id: Int): Flow<Character> = flow {
        val response = network.getSingleCharacter(id)
        val result = response.asModel()
        emit(result)
    }.flowOn(Dispatchers.IO)

    override fun getMultipleCharacterFlow(ids: List<Int>): Flow<List<Character>> = flow {
        val response = network.getMultipleCharacters(ids)
        val result = response.map(CharacterDto::asModel)
        emit(result)
    }.flowOn(Dispatchers.IO)
}