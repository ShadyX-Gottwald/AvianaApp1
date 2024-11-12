package skosana.avianaapp1.Presentation.Navigation

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import skosana.avianaapp1.Domain.Constants.AuthenticationState
import skosana.avianaapp1.Features.Settings.LanguageSettings.LanguageSettingsImpl
import skosana.avianaapp1.Features.Settings.MeasurementUnitSettings.MeasurementUnitSettingsImpl
import skosana.avianaapp1.Features.Settings.NotificationSettings.NotificationSettingImpl
import skosana.avianaapp1.Features.Settings.SpeciesRegionSettings.BirdSpeciesRegionImpl
import skosana.avianaapp1.Utils.NetworkResponse

class SettingsViewModel(
    private val notificationsUser: NotificationSettingImpl ,
    private val languageSettings: LanguageSettingsImpl ,
    private val speciesRegionSettings: BirdSpeciesRegionImpl ,
    private val measureInKilometersSettings: MeasurementUnitSettingsImpl
): ViewModel() {

    //Get Notification Settings of User
    val settings = notificationsUser.noti_settings_flow.map {  canNotify ->
        canNotify
    }

    //Get Bird Lang Settings of User
    val birdingLanguage = languageSettings.lang_settings_flow.map {
        lang -> lang
    }
    private val _birdlangPrefSwitch = MutableStateFlow(false)
    val birdlangPrefSwitch = _birdlangPrefSwitch.asStateFlow()


    //Species Region Settings variables
    val speciesRegion = speciesRegionSettings.species_region_settings_flow
        .map {
            region -> region
    }
    private val _speciesRegionsPrefSwitch = MutableStateFlow(true)
    val speciesRegionsPrefSwitch = _speciesRegionsPrefSwitch.asStateFlow()

    //Distance Settings Variables
    val measureInKilometers = measureInKilometersSettings.measure_unit_settings_flow
        .map {
                region -> region
        }
    private val _measureInKilometersPrefSwitch = MutableStateFlow(true)
    val measureInKilometersPrefSwitch = _speciesRegionsPrefSwitch.asStateFlow()

    //Update Notification Setting
    fun updateCanNotify(canNotify: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            notificationsUser.saveNotificationSettings(canNotify)
        }
    }
    fun updateBirdPrefLang(language: String) {
        viewModelScope.launch(Dispatchers.IO) {
            languageSettings.saveBirdPrefLangSettings(language)
        }

    }
    fun updateSpeciesRegion(region: String) {
        viewModelScope.launch(Dispatchers.IO){
            speciesRegionSettings.saveBirdSpeciesRegionSettings(region)
        }
    }

    fun updateMeasureInKilometers(value: Boolean) {
        viewModelScope.launch {
            measureInKilometersSettings.saveMeasurementUnitSettings(value)
        }
    }

}