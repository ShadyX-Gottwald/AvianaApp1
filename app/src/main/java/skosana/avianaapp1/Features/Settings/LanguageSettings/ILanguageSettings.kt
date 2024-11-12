package skosana.avianaapp1.Features.Settings.LanguageSettings

import kotlinx.coroutines.flow.Flow

interface ILanguageSettings {

    suspend fun saveBirdPrefLangSettings(language:String)
    fun getBirdPrefLangSettings(): Flow<String>
}