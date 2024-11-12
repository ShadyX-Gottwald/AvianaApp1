package skosana.avianaapp1.Features.Settings.LanguageSettings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import skosana.avianaapp1.Features.Settings.NotificationSettings.NotificationSettingImpl



class LanguageSettingsImpl(
    private val context: Context
):ILanguageSettings {

    companion object {
        //Kilometer Unit Settings
        private val Context.langStore: DataStore<Preferences> by preferencesDataStore("userLanguage")
        private val USER_LANGUAGE_KEY = stringPreferencesKey("user_language")

    }

    val lang_settings_flow = context.langStore.data.catch { exception ->

        if(exception is IOException) {
            emit(emptyPreferences())
        }else {
            throw exception
        }
    }.map { preferences ->
        val lang = preferences[USER_LANGUAGE_KEY]?: "English"
        lang
    }

    override suspend fun saveBirdPrefLangSettings(language: String) {
        context.langStore.edit { pref ->
            pref[USER_LANGUAGE_KEY] = language
        }
    }

    override fun getBirdPrefLangSettings(): Flow<String> {
        TODO("Not yet implemented")
    }

}