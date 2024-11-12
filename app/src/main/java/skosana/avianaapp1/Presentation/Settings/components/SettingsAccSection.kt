package skosana.avianaapp1.Presentation.Settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import skosana.avianaapp1.Presentation.Settings.ComponentsNavTo.NotificationSettingPage
import skosana.avianaapp1.Presentation.Settings.constants.SettingsPageConstants
import skosana.avianaapp1.Presentation.Settings.constants.SettingsPageConstants.SettingAccItems

@Composable
fun SettingsAccSection(
    NavToNotificationSettings: () -> Unit ,
    NavToPrivacySettings: () -> Unit
) {
    var accountIndex by remember { mutableStateOf(100) }
    LazyColumn(
      //  modifier = Modifier.verticalScroll(rememberScrollState()),
        contentPadding = PaddingValues(15.dp)
    ) {

        items(SettingAccItems.size) { item ->
            Row (
                modifier = Modifier
                    .padding(vertical = 15.dp)


            ){

                ListItem(modifier = Modifier.clickable {
                    accountIndex = item

                },
                    headlineContent = {
                    Text(text = SettingsPageConstants.SettingAccItems[item].label , fontSize = 16.sp)

                }, leadingContent = {
                    Icon(imageVector = SettingAccItems[item].icon, contentDescription = "Item" )
                }, trailingContent = {
                    Icon(imageVector = Icons.Outlined.ChevronRight, contentDescription = "Item" )

                } ,)


            }
            HorizontalDivider()

        }
        when(accountIndex){
            0 -> NavToNotificationSettings()
            1 -> NavToPrivacySettings()

            else -> Unit
        }

    }
}