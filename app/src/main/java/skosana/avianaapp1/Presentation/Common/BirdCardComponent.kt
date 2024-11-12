package skosana.avianaapp1.Presentation.Common

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import skosana.avianaapp1.R
import skosana.avianaapp1.ui.theme.DarkPurpleBackGround
import skosana.avianaapp1.ui.theme.Peach


@Composable
fun BirdCardComponent(
    // 1. Efficient Image Handling with Coil
    imagePainter: Painter = rememberAsyncImagePainter(model = R.drawable.avianalogo),
    imageSize: Dp = 80.dp,
    imageShape: Shape = RoundedCornerShape(8.dp),
    titleText: String = "John Doe",
    titleFontSize: TextUnit = 30.sp,
    titleTextColor: Color = Color(0xFFBEC2C5),
    subtitleText: String = "Software Engineer",
    otherName: String = "Other Name",
    dateText: String = "Date",
    subtitleFontSize: TextUnit = 16.sp,
    subtitleTextColor: Color = Color(0xFF9E9FA0),
    backgroundColor: Color = Color(0xFF283542),
    contentPadding: Dp = 16.dp,
    onViewButtonClick: () -> Unit = {}
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable{ },
        elevation = CardDefaults.cardElevation()

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Image(imagePainter,contentDescription = null,Modifier.fillMaxWidth(1F).height(150.dp))

        }
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(text = titleText, fontSize = titleFontSize

            )
            Text(text = otherName, fontSize = subtitleFontSize, fontWeight = FontWeight.Black)
            BirdLocationRow(subtitleText)
           // Text(text = "Location", fontSize = subtitleFontSize, fontWeight = FontWeight.Black)
           // Text(text = dateText, fontSize = subtitleFontSize, fontWeight = FontWeight.Medium)
            BirdDateRow(dateText ,subtitleFontSize)
        }
        ViewButtonComponent(onViewButtonClick,titleText,subtitleText)
    }
}

@Composable
fun BirdLocationRow(location: String) {
    Row() {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Home Icon",
            tint = Color.Red,
            modifier = Modifier.size(20.dp)
        )
        //Image(imageVector = Icons.Default.LocationOn, contentDescription = null, modifier = Modifier.size(20.dp))
        Text(text = location)
    }
}

@Composable
fun BirdDateRow(date: String, subtitleFontSize: TextUnit,) {
    Row() {
        Icon(
            imageVector = Icons.Default.RemoveRedEye,
            contentDescription = "Home Icon",
            tint = Peach,
            modifier = Modifier.size(20.dp)
        )
        //Image(imageVector = Icons.Default.LocationOn, contentDescription = null, modifier = Modifier.size(20.dp))
        Text(text = date, fontSize = subtitleFontSize, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun ViewButtonComponent(onViewButtonClick: () -> Unit ,subtitleText: String ,titleText: String ) {
    val context = LocalContext.current.applicationContext as Application
    val dialogState = remember { mutableStateOf(false) }
    Row(modifier = Modifier.padding(15.dp) ,
        Arrangement.End) {
        Button(
            modifier = Modifier.weight(1f),
            onClick = { onViewButtonClick()
                CoroutineScope(CoroutineName("NotificationScope")).launch {

                    dialogState.value = true
                }
                      },
            colors = ButtonDefaults.buttonColors(containerColor = DarkPurpleBackGround),
            border = getButtonBorder(DarkPurpleBackGround, Color.White),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                "View",
                color = Color.White,
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
        if (dialogState.value) {
            NotificationDialog(name = titleText,subtitleText, onDismiss = { dialogState.value = false })
        }
    }
}



