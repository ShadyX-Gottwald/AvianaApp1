package skosana.avianaapp1.Presentation.Settings.ComponentsNavTo

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import skosana.avianaapp1.Presentation.Settings.components.SettingsTopBar

@Composable
fun PrivacySettingsPage(
    context: Context,
    NavBackClick: () -> Unit
) {
    Scaffold (
        topBar = { SettingsTopBar {
            NavBackClick()
        }
        }

    ){

        Column(
            modifier = Modifier
                .fillMaxSize(1F)
                .background(Color.White)

        ) {
            Spacer(modifier = Modifier.padding(it))
            Text(text = "Birding Preferences", color = Color.Black , fontSize = 25.sp, fontWeight = FontWeight.W800 )
        }

    }


}