package skosana.avianaapp1.Features.GoogleMap.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import skosana.avianaapp1.Features.GoogleMap.Utils.LocationUtils

@Composable
fun LocationPermissionAndSettingDialog(updateCurrentLocation: () -> Unit,){

    var requestLocationSetting by remember { mutableStateOf(false) }

    if(LocationUtils.isLocationPermissionGranted(LocalContext.current)) {
        SideEffect {
            requestLocationSetting = true
        }
    } else {

        LocationSettingDialog(
            onPermissionGranted = {
                requestLocationSetting = true
            },
            onPermissionDenied = {
                requestLocationSetting = true
            }
        )

    }

    if (requestLocationSetting) {

        LocationSettingDialogs(onSuccess = {
            requestLocationSetting = false
            updateCurrentLocation()
        }, onFailure = {
            requestLocationSetting = false
            updateCurrentLocation()
        })


    }
}