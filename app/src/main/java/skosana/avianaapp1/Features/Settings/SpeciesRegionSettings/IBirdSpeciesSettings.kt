package skosana.avianaapp1.Features.Settings.SpeciesRegionSettings

import kotlinx.coroutines.flow.Flow

interface IBirdSpeciesSettings {
    suspend fun saveBirdSpeciesRegionSettings(region:String)
    fun getBirdPrefLangSettings(): Flow<String>
}