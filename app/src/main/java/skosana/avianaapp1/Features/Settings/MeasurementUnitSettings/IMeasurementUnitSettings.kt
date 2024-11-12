package skosana.avianaapp1.Features.Settings.MeasurementUnitSettings

import kotlinx.coroutines.flow.Flow

interface IMeasurementUnitSettings {
    suspend fun saveMeasurementUnitSettings(kilometers: Boolean)
    fun getNotificationSettings(): Flow<Boolean>
}