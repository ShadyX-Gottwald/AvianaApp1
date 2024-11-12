package skosana.avianaapp1.Presentation.Settings.components

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import skosana.avianaapp1.ui.theme.DarkPurpleBackGround
import skosana.avianaapp1.ui.theme.Peach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopBar(NavBackToClick: () -> Unit
) {
    CenterAlignedTopAppBar(title = {
        Text(text = "Settings", color = Peach  )

    }, modifier = Modifier.background(DarkPurpleBackGround),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = DarkPurpleBackGround,
        titleContentColor = Peach
    ),
        navigationIcon = {

          IconButton(onClick = { NavBackToClick() }) {
              Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "" , tint = Peach )
          }
        }
    )
}