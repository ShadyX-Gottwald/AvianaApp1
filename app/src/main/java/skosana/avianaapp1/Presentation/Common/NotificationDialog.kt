package skosana.avianaapp1.Presentation.Common

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import skosana.avianaapp1.Features.Notifications.NotificationViewModel
import java.util.concurrent.TimeUnit

@Composable
fun NotificationDialog(
    name: String ,
    SciName: String,
    onDismiss: () -> Unit
) {

    val context = LocalContext.current.applicationContext as Application


    val viewModel by lazy { NotificationViewModel(context) }

    val schedules = listOf(
        "5 seconds" to 5000L,
        "8 Minutes" to 8 * 60 * 1000L,
         "1 Day" to 24 * 60 * 60 * 1000L,
        "1 Week" to 7 * 24 * 60 * 60 * 1000L
    )



    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth().testTag("reminderDialog")) {
                Text(
                    text = "Reminder",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 16.dp).fillMaxWidth()
                )

                schedules.forEach { (scheduleTextId, delayMillis) ->
                    ListItem(
                        headlineContent = { Text(text = scheduleTextId) },
                        modifier = Modifier.clickable {
                            viewModel.scheduleReminder(delayMillis, TimeUnit.MILLISECONDS,Name = name, SciName = SciName)
                            onDismiss()
                        }
                    )
                }
            }
        }
    }
}