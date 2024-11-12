package skosana.avianaapp1.Features.Settings.NotificationSettings

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


class NotificationSettingImpl(
    private val context: Context
): INotificationSettings {

    companion object {
        //Kilometer Unit Settings
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userNotification")
        private val USER_Notification_KEY = booleanPreferencesKey("user_notification")

    }


    val noti_settings_flow = context.dataStore.data.catch { exception ->

        if(exception is IOException) {
            emit(emptyPreferences())
        }else {
            throw exception
        }
    }.map { preferences ->
        val toggled = preferences[USER_Notification_KEY]?: false
        toggled
    }
    override suspend fun saveNotificationSettings(canNotify: Boolean) {
         context.dataStore.edit { pref ->
            pref[USER_Notification_KEY] = canNotify
        }
    }


    override  fun getNotificationSettings(): Flow<Boolean> = context.dataStore.data
        .map { pref ->
            pref[USER_Notification_KEY]?: false

        }


}