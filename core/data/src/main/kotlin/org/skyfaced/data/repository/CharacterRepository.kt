package org.skyfaced.data.repository

import kotlinx.coroutines.flow.Flow
import org.skyfaced.data.model.Character
import org.skyfaced.data.model.InfoWrapper
import org.skyfaced.network.model.filter.CharacterFilter

interface CharacterRepository {
    /**
     * Gets the available [Character] as a flow
     */
    fun getAllCharactersFlow(page: Int, filter: CharacterFilter): Flow<InfoWrapper<Character>>

    /**
     * Gets data for a specific [Character] as a flow
     */
    fun getSingleCharacterFlow(id: Int): Flow<Character>

    /**
     * Gets the available [Character] as a flow
     */
    fun getMultipleCharacterFlow(ids: List<Int>): Flow<List<Character>>
}