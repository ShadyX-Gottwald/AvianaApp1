package skosana.avianaapp1.Features.Settings.MeasurementUnitSettings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import skosana.avianaapp1.Features.Settings.NotificationSettings.NotificationSettingImpl

class MeasurementUnitSettingsImpl(
    private val context: Context
):IMeasurementUnitSettings {

    companion object {
        //Kilometer Unit Settings
        private val Context.measureUnitStore: DataStore<Preferences> by preferencesDataStore("userMeasureUnit")
        private val USER_MEASURE_UNIT_KEY = booleanPreferencesKey("user_measure_unit")

    }

    val measure_unit_settings_flow = context.measureUnitStore.data.catch { exception ->

        if(exception is IOException) {
            emit(emptyPreferences())
        }else {
            throw exception
        }
    }.map { preferences ->
        val toggled = preferences[USER_MEASURE_UNIT_KEY]?: true
        toggled
    }
    override suspend fun saveMeasurementUnitSettings(kilometers: Boolean) {
        context.measureUnitStore.edit { pref ->
            pref[USER_MEASURE_UNIT_KEY] = kilometers
        }
    }

    override fun getNotificationSettings(): Flow<Boolean> {
        TODO("Not yet implemented")
    }
}