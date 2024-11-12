package skosana.avianaapp1.Features.Settings.NotificationSettings

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface INotificationSettings {
    suspend fun saveNotificationSettings(canNotify: Boolean)
     fun getNotificationSettings(): Flow<Boolean>
}