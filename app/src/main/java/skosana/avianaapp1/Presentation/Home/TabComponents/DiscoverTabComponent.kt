package skosana.avianaapp1.Presentation.Home.TabComponents

import android.Manifest
import android.Manifest.*
import android.Manifest.permission.*
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.PackageManager.*
import android.os.Build
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.checkSelfPermission
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import skosana.avianaapp1.Features.Notifications.NotificationService
import skosana.avianaapp1.Presentation.Common.BirdCardComponent
import skosana.avianaapp1.Presentation.Home.REQUEST_CODE_NOTIFICATION_PERMISSIONS
import skosana.avianaapp1.Presentation.Navigation.BirdViewModel
import skosana.avianaapp1.Presentation.Navigation.MapsViewModel
import skosana.avianaapp1.Presentation.Navigation.SettingsViewModel
import skosana.avianaapp1.Presentation.Settings.Utils.LanguageSettingsUtils
import skosana.avianaapp1.ui.theme.WhiteishBg

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun DiscoverTabComponent(
    mapsViewModel: MapsViewModel,
    birdViewModel: BirdViewModel,
    context: Context,
    settingsViewModel: SettingsViewModel,

) {
    val Name = CoroutineName("DiscoverTabComponent")
    val Scope = CoroutineScope(Name)
    var languageSettings by remember { mutableStateOf("English")}

    val notificationService = NotificationService(LocalContext.current.applicationContext)

    LaunchedEffect(true) {
        Scope.launch(Dispatchers.IO) {
            settingsViewModel.birdingLanguage.collect {
                prefsLang -> Log.d("DiscoverTabComponent", "Prefs Language: $prefsLang")
                languageSettings = prefsLang

                Log.i("DiscoverTabComponent", "Language: ${languageSettings}")
                val queryLang = LanguageSettingsUtils.Languagess[languageSettings]
                Log.i("DiscoverTabComponent", "Query Language: $queryLang")
                if (queryLang != null) {
                    birdViewModel.getTaxonomicBirds(queryLang)
                }

            }

        }

    }
    Scaffold (


    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(1F)
                .background(WhiteishBg)
                .padding(vertical = 6.dp)

        ) {
            Spacer(modifier = Modifier.padding(it))
            Column(
            ) {
                ShowDefaultBirdsDiscover(viewModel = mapsViewModel, birdViewModel = birdViewModel,notificationService)

            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
 fun ShowDefaultBirdsDiscover(
    viewModel: MapsViewModel,
    birdViewModel: BirdViewModel,
    notificationService: NotificationService
) {
    val birds = birdViewModel.BirdsDefault.collectAsState()
    val photourls = birdViewModel.BirdPhotoUrls.collectAsState()
    val context = LocalContext.current

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getNotificationPermissions(context)
    }
    LaunchedEffect(true) {
        getNotificationPermissions(context)
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(vertical = 6.dp)
    ) {
        val responseRoute  = mutableStateOf("")


        birds.value.take(30).forEachIndexed {index, it ->
            item {

                BirdCardComponent(
                    titleText = it.sciName,
                    subtitleText = it.comName,
                    otherName = it.familyCode,
                    dateText = it.category,
                    onViewButtonClick = { showNotification( notificationService) }

                )
                }
            }
        }
    }

private fun showNotification( notificationService: NotificationService) {
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    //notificationService.showNotification()
}
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
private fun getNotificationPermissions(context: Context) {
    try {
        // Check if the app already has the permissions.
        val hasAccessNotificationPolicyPermission =
            checkSelfPermission(context, ACCESS_NOTIFICATION_POLICY) != PermissionChecker.PERMISSION_GRANTED
        val hasPostNotificationsPermission =
            checkSelfPermission(context,POST_NOTIFICATIONS) != PermissionChecker.PERMISSION_GRANTED

        // If the app doesn't have the permissions, request them.
        when {
            !hasAccessNotificationPolicyPermission || !hasPostNotificationsPermission -> {
                // Request the permissions.
                ActivityCompat.requestPermissions(
                    context as androidx.fragment.app.FragmentActivity,
                    arrayOf(
                        ACCESS_NOTIFICATION_POLICY,
                        POST_NOTIFICATIONS
                    ),
                    REQUEST_CODE_NOTIFICATION_PERMISSIONS
                )
            }
            else -> {
                // proceed
               // Log.d(TAG, "Notification Permissions : previously granted successfully")
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

}



