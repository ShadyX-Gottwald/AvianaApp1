package skosana.avianaapp1.Features.Settings.SpeciesRegionSettings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import skosana.avianaapp1.Features.Settings.LanguageSettings.LanguageSettingsImpl

class BirdSpeciesRegionImpl(
    private val context: Context
): IBirdSpeciesSettings {

    companion object {
        //BirdSpecies Region Settings
        private val Context.regionStore: DataStore<Preferences> by preferencesDataStore("userSpeciesRegion")
        private val USER_SPECIES_REGION_KEY = stringPreferencesKey("user_species_region")

    }

    val species_region_settings_flow = context.regionStore.data.catch { exception ->

        if(exception is IOException) {
            emit(emptyPreferences())
        }else {
            throw exception
        }
    }.map { preferences ->
        val region = preferences[USER_SPECIES_REGION_KEY]?: "Southern Africa"
        region
    }

    override suspend fun saveBirdSpeciesRegionSettings(region: String) {
        context.regionStore.edit { pref ->
            pref[USER_SPECIES_REGION_KEY] = region
        }
    }

    override fun getBirdPrefLangSettings(): Flow<String> {
        TODO("Not yet implemented")
    }
}